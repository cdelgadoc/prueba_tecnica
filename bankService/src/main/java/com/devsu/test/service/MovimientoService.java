package com.devsu.test.service;

import com.devsu.test.dto.MovimientoDTO;
import com.devsu.test.dto.ReporteEstadoCuentaDTO;
import com.devsu.test.model.Movimiento;

import java.util.Date;

public interface MovimientoService {

    Iterable<ReporteEstadoCuentaDTO> generarReporteEstadoCuenta(Date fechaInicial, Date fechaFinal, String clienteId) ;

    Movimiento createMovimiento(MovimientoDTO movimientoDTO);

}
