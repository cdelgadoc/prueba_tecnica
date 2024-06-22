package com.devsu.test.service;

import com.devsu.test.model.Cliente;

import java.util.Optional;

public interface ClienteService {

    Iterable<Cliente> getAllClientes();

    Optional<Cliente> getClienteByClienteId(String clienteId);

    Cliente createCliente(Cliente cliente);

    Cliente updateCliente(Long id, Cliente cliente);

    Cliente updateEstadoCliente(Long id);

    Boolean deleteCliente(Long id);

}
