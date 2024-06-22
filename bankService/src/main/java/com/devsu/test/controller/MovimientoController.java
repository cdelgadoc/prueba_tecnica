package com.devsu.test.controller;

import com.devsu.test.dto.MovimientoDTO;
import com.devsu.test.model.Movimiento;
import com.devsu.test.service.MovimientoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimientos")
@Validated
public class MovimientoController {

    private final MovimientoService movimientoService;

    @Autowired
    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public ResponseEntity<Movimiento> crearMovimiento(@Valid @RequestBody MovimientoDTO movimientoDTO) {
        return new ResponseEntity<>(movimientoService.createMovimiento(movimientoDTO), HttpStatus.CREATED);
    }

}
