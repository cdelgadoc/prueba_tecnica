package com.devsu.test.service;

import com.devsu.test.model.Cliente;
import com.devsu.test.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Iterable<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> getClienteByClienteId(String clienteId) {
        return clienteRepository.findByClienteId(clienteId);
    }

    @Override
    public Cliente createCliente(Cliente cliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findByClienteId(cliente.getClienteId());
        if (!clienteOptional.isPresent()) {
            return clienteRepository.save(cliente);
        }
        return clienteOptional.get();
    }

    @Override
    public Cliente updateCliente(Cliente cliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findByClienteId(cliente.getClienteId());
        if (clienteOptional.isPresent()) {
            Cliente existingCliente = clienteOptional.get();
            existingCliente.setNombre(cliente.getNombre());
            existingCliente.setEstado(cliente.getEstado());
            return clienteRepository.save(existingCliente);
        }
        return null;
    }

    @Override
    public void desactivarCliente(String clienteId) {
        Cliente cliente = clienteRepository.findByClienteId(clienteId).orElse(null);
        if (cliente != null) {
            cliente.setEstado(Boolean.FALSE);
            clienteRepository.save(cliente);
        }
    }

}
