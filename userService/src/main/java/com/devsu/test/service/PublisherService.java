package com.devsu.test.service;

import com.devsu.test.dto.ClienteDTO;
import com.devsu.test.queue.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

    @Value("${accion.cliente.crear}")
    private String accionCrearCliente;

    @Value("${accion.cliente.actualizar}")
    private String accionCrearActualizar;

    @Value("${accion.cliente.borrar}")
    private String accionCrearBorrar;

    private final MessageSender messageSender;

    @Autowired
    public PublisherService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void crearCliente(ClienteDTO cliente) {
        messageSender.enviarMensaje(accionCrearCliente, cliente);
    }

    public void actualizarCliente(ClienteDTO cliente) {
        messageSender.enviarMensaje(accionCrearActualizar, cliente);
    }

    public void borrarCliente(ClienteDTO cliente) {
        messageSender.enviarMensaje(accionCrearBorrar, cliente);
    }

}
