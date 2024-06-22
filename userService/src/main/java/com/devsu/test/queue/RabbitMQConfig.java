package com.devsu.test.queue;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.cliente}")
    private String clienteExchange;

    @Value("${rabbitmq.queue.crearcliente}")
    private String queueCrearCliente;

    @Value("${rabbitmq.queue.actualizarcliente}")
    private String queueActualizarCliente;

    @Value("${rabbitmq.queue.borrarcliente}")
    private String queueBorrarCliente;

    @Value("${accion.cliente.crear}")
    private String accionCrearCliente;

    @Value("${accion.cliente.actualizar}")
    private String accionCrearActualizar;

    @Value("${accion.cliente.borrar}")
    private String accionCrearBorrar;

    private Channel channel;

    @Autowired
    public RabbitMQConfig(Channel channel) {
        this.channel = channel;
    }

    @PostConstruct
    public void setupRabbitMQ() throws IOException {
        // Crear exchange
        channel.exchangeDeclare(clienteExchange, BuiltinExchangeType.TOPIC);

        // Crear colas
        channel.queueDeclare(queueCrearCliente, true, false, false, null);
        channel.queueDeclare(queueActualizarCliente, true, false, false, null);
        channel.queueDeclare(queueBorrarCliente, true, false, false, null);

        // Vincular colas al exchange con patrones de enrutamiento
        channel.queueBind(queueCrearCliente, clienteExchange, accionCrearCliente);
        channel.queueBind(queueActualizarCliente, clienteExchange, accionCrearActualizar);
        channel.queueBind(queueBorrarCliente, clienteExchange, accionCrearBorrar);

        System.out.println("Setup de RabbitMQ completado.");
    }

    @Bean
    public TopicExchange clienteExchange() {
        return new TopicExchange(clienteExchange);
    }

    @Bean
    public Queue clienteCrearQueue() {
        return new Queue(queueCrearCliente);
    }

    @Bean
    public Queue clienteActualizarQueue() {
        return new Queue(queueActualizarCliente);
    }

    @Bean
    public Queue clienteBorrarQueue() {
        return new Queue(queueBorrarCliente);
    }

    @Bean
    public Binding clienteCrearBinding(TopicExchange exchange, Queue clienteCrearQueue) {
        return BindingBuilder.bind(clienteCrearQueue).to(exchange).with(accionCrearCliente);
    }

    @Bean
    public Binding clienteActualizarBinding(TopicExchange exchange, Queue clienteActualizarQueue) {
        return BindingBuilder.bind(clienteActualizarQueue).to(exchange).with(accionCrearActualizar);
    }

    @Bean
    public Binding clienteBorrarBinding(TopicExchange exchange, Queue clienteBorrarQueue) {
        return BindingBuilder.bind(clienteBorrarQueue).to(exchange).with(accionCrearBorrar);
    }

}
