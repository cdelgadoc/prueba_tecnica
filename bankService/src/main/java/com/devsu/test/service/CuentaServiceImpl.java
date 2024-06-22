package com.devsu.test.service;

import com.devsu.test.dto.CuentaDTO;
import com.devsu.test.exception.ClienteDesabilitadoException;
import com.devsu.test.exception.ClienteNotFoundException;
import com.devsu.test.model.Cliente;
import com.devsu.test.model.Cuenta;
import com.devsu.test.model.TipoCuenta;
import com.devsu.test.repository.ClienteRepository;
import com.devsu.test.repository.CuentaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    public CuentaServiceImpl(CuentaRepository cuentaRepository, ClienteRepository clienteRepository) {
        this.cuentaRepository = cuentaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Iterable<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    @Override
    public List<Cuenta> getCuentaByClienteId(String clienteId) {
        return cuentaRepository.findByClienteId(clienteId);
    }

    @Override
    public Cuenta createCuenta(CuentaDTO cuentaDTO) {
        Cliente cliente = clienteRepository.findByClienteId(cuentaDTO.getClienteId())
                .orElseThrow(() -> new ClienteNotFoundException());

        if (cliente.getEstado()) {
            Cuenta cuenta = new Cuenta();
            cuenta.setTipoCuenta(TipoCuenta.valueOf(cuentaDTO.getTipoCuenta().toUpperCase()));
            cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
            cuenta.setSaldoInicial(cuentaDTO.getSaldoInicial());
            cuenta.setSaldoDisponible(cuenta.getSaldoInicial());
            cuenta.setEstado(cuentaDTO.getEstado());
            cuenta.setCliente(cliente);

            return cuentaRepository.save(cuenta);
        } else {
            throw new ClienteDesabilitadoException();
        }
    }


    @Override
    public Cuenta updateSaldoCuenta(Long id, BigDecimal saldo) {
        Cuenta cuenta = cuentaRepository.findById(id).orElse(null);
        if (cuenta != null) {
            cuenta.setSaldoDisponible(saldo);
            cuenta = cuentaRepository.save(cuenta);
        }
        return cuenta;
    }

    @Override
    public Boolean cerrarCuenta(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id).orElse(null);
        if (cuenta != null) {
            cuenta.setEstado(Boolean.FALSE);
            cuentaRepository.save(cuenta);
            return true;
        }

        return false;
    }

}
