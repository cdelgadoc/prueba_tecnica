package com.devsu.test.controller;

import com.devsu.test.dto.ReporteEstadoCuentaDTO;
import com.devsu.test.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/reportes")
@Validated
public class ReporteController {

    private final MovimientoService movimientoService;

    @Autowired
    public ReporteController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping
    public ResponseEntity<Iterable<ReporteEstadoCuentaDTO>> generarReporteEstadoCuenta(
            @RequestParam String clienteId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicial,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFinal) {
        return new ResponseEntity<>(movimientoService.generarReporteEstadoCuenta(fechaInicial, fechaFinal, clienteId), HttpStatus.OK);
    }

}
