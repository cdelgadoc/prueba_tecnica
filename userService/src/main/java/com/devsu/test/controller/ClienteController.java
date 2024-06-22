package com.devsu.test.controller;

import com.devsu.test.model.Cliente;
import com.devsu.test.service.ClienteService;
import com.devsu.test.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
@Validated
public class ClienteController {

    @Value("${messagge.cliente.delete}")
    private String clienteBorrado;

    @Value("${messagge.cliente.notfound}")
    private String clienteNoEncontrado;

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Cliente>> getAllClientes() {
        return new ResponseEntity<>(clienteService.getAllClientes(), HttpStatus.OK);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> getClienteByClienteId(@PathVariable String clienteId) {
        return clienteService.getClienteByClienteId(clienteId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@Valid @RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.createCliente(cliente), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetails) {
        Cliente updatedCliente = clienteService.updateCliente(id, clienteDetails);
        return (updatedCliente != null) ? ResponseEntity.ok(updatedCliente) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Cliente> updateEstadoCliente(@PathVariable Long id) {
        Cliente updatedCliente = clienteService.updateEstadoCliente(id);
        return (updatedCliente != null) ? ResponseEntity.ok(updatedCliente) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCliente(@PathVariable Long id) {
        return clienteService.deleteCliente(id) ?
                ResponseEntity.ok(new ApiResponse(clienteBorrado)) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(clienteNoEncontrado));
    }

}
