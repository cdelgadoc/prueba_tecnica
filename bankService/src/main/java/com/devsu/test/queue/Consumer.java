package com.devsu.test.queue;

import com.devsu.test.model.Cliente;
import com.devsu.test.service.ClienteService;
import com.devsu.test.utils.ClienteUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private final ClienteService clienteService;

    public Consumer(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @RabbitListener(queues = { "${rabbitmq.queue.crearcliente}" })
    public void recibirMensajeCrearCliente(@Payload String mensaje) {
        Cliente cliente = ClienteUtil.getCliente(mensaje);
        if (cliente != null) {
            clienteService.createCliente(cliente);
        }
    }

    @RabbitListener(queues = { "${rabbitmq.queue.actualizarcliente}" })
    public void recibirMensajeActualizarCliente(@Payload String mensaje) {
        Cliente cliente = ClienteUtil.getCliente(mensaje);
        if (cliente != null) {
            clienteService.updateCliente(cliente);
        }
    }

    @RabbitListener(queues = { "${rabbitmq.queue.borrarcliente}" })
    public void recibirMensajeBorrarCliente(@Payload String mensaje) {
        Cliente cliente = ClienteUtil.getCliente(mensaje);
        clienteService.desactivarCliente(cliente.getClienteId());
    }

}
