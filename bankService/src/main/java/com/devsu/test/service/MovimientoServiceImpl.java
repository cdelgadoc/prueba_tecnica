package com.devsu.test.service;

import com.devsu.test.dto.MovimientoDTO;
import com.devsu.test.dto.ReporteEstadoCuentaDTO;
import com.devsu.test.exception.CuentaDesabilitadaException;
import com.devsu.test.exception.CuentaNotFoundException;
import com.devsu.test.exception.SaldoInsuficienteException;
import com.devsu.test.model.Cuenta;
import com.devsu.test.model.Movimiento;
import com.devsu.test.model.TipoMovimiento;
import com.devsu.test.repository.CuentaRepository;
import com.devsu.test.repository.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository,
                                 CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public Iterable<ReporteEstadoCuentaDTO> generarReporteEstadoCuenta(Date fechaInicial, Date fechaFinal, String clienteId) {
        return movimientoRepository.generarReporteEstadoCuenta(fechaInicial, fechaFinal, clienteId);
    }

    @Override
    public Movimiento createMovimiento(MovimientoDTO movimientoDTO) {
        Cuenta cuenta = cuentaRepository.findById(movimientoDTO.getCuentaId())
                .orElseThrow(() -> new CuentaNotFoundException());

        if (cuenta.getEstado()) {
            if (cuenta.getSaldoDisponible().compareTo(BigDecimal.ZERO) > 0 ||
                    TipoMovimiento.DEPOSITO.equals(TipoMovimiento.valueOf(movimientoDTO.getTipoMovimiento().toUpperCase()))) {
                Movimiento movimiento = new Movimiento();
                movimiento.setFecha(new Date());
                movimiento.setTipoMovimiento(TipoMovimiento.valueOf(movimientoDTO.getTipoMovimiento().toUpperCase()));
                movimiento.setValor(movimientoDTO.getValor());

                BigDecimal nuevoSaldo = cuenta.getSaldoDisponible();
                if (TipoMovimiento.DEPOSITO.equals(TipoMovimiento.valueOf(movimientoDTO.getTipoMovimiento().toUpperCase()))) {
                    nuevoSaldo = nuevoSaldo.add(movimientoDTO.getValor());
                } else if (TipoMovimiento.RETIRO.equals(TipoMovimiento.valueOf(movimientoDTO.getTipoMovimiento().toUpperCase()))) {
                    nuevoSaldo = nuevoSaldo.subtract(movimientoDTO.getValor());
                }

                cuenta.setSaldoDisponible(nuevoSaldo);
                cuenta = cuentaRepository.save(cuenta);

                movimiento.setSaldoDisponible(nuevoSaldo);
                movimiento.setCuenta(cuenta);
                return movimientoRepository.save(movimiento);
            }
            throw new SaldoInsuficienteException();
        } else {
            throw new CuentaDesabilitadaException();
        }
    }

}
