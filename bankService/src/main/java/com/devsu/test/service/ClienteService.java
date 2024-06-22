package com.devsu.test.service;

import com.devsu.test.model.Cliente;

import java.util.Optional;

public interface ClienteService {

    Iterable<Cliente> getAllClientes();

    Optional<Cliente> getClienteByClienteId(String clienteId);

    Cliente createCliente(Cliente cliente);

    Cliente updateCliente(Cliente cliente);

    void desactivarCliente(String clienteId);

}
