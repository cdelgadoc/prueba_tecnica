package com.devsu.test.service;

import com.devsu.test.dto.CuentaDTO;
import com.devsu.test.model.Cuenta;

import java.math.BigDecimal;
import java.util.List;

public interface CuentaService {

    Iterable<Cuenta> getAllCuentas();

    List<Cuenta> getCuentaByClienteId(String clienteId);

    Cuenta createCuenta(CuentaDTO cuentaDTO);

    Cuenta updateSaldoCuenta(Long id, BigDecimal saldo);

    Boolean cerrarCuenta(Long id);

}
