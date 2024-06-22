package com.devsu.test.controller;

import com.devsu.test.dto.CuentaDTO;
import com.devsu.test.model.Cuenta;
import com.devsu.test.service.CuentaService;
import com.devsu.test.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cuentas")
@Validated
public class CuentaController {

    private final CuentaService cuentaService;

    @Autowired
    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Cuenta>> getAllCuentas() {
        return new ResponseEntity<>(cuentaService.getAllCuentas(), HttpStatus.OK);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Iterable<Cuenta>> getCuentasByClienteId(@PathVariable String clienteId) {
        return new ResponseEntity<>(cuentaService.getCuentaByClienteId(clienteId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@Valid @RequestBody CuentaDTO cuentaDTO) {
        return new ResponseEntity<>(cuentaService.createCuenta(cuentaDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> cerrarCuenta(@PathVariable Long id) {
        return cuentaService.cerrarCuenta(id) ?
                ResponseEntity.ok(new ApiResponse("Cuenta cerrada exitosamente")) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Cuenta no encontrada"));
    }

}
