package com.devsu.test.queue;

import com.devsu.test.dto.ClienteDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Value("${rabbitmq.exchange.cliente}")
    private String clienteExchange;

    private final AmqpTemplate rabbitTemplate;

    @Autowired
    public MessageSender(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensaje(String accion, ClienteDTO cliente) {
        rabbitTemplate.convertAndSend(clienteExchange, accion, cliente.toString());
    }

}
