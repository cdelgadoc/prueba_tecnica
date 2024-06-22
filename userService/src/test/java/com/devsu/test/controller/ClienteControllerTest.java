package com.devsu.test.controller;

import com.devsu.test.model.Cliente;
import com.devsu.test.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    private MockMvc mockMvc;

    @Test
    void testGetAllClientes() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
        when(clienteService.getAllClientes()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(clienteService, times(1)).getAllClientes();
    }

    @Test
    void testGetClienteByClienteId() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
        Cliente cliente = new Cliente();
        cliente.setIdentificacion("123");

        when(clienteService.getClienteByClienteId("123")).thenReturn(Optional.of(cliente));

        mockMvc.perform(get("/clientes/123"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.clienteId").value("123"));

        verify(clienteService, times(1)).getClienteByClienteId("123");
    }

    @Test
    void testGetClienteByClienteIdNotFound() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
        when(clienteService.getClienteByClienteId("123")).thenReturn(Optional.empty());

        mockMvc.perform(get("/clientes/123"))
                .andExpect(status().isNotFound());

        verify(clienteService, times(1)).getClienteByClienteId("123");
    }

    @Test
    void testCreateCliente() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
        Cliente cliente = new Cliente();
        cliente.setIdentificacion("123");
        cliente.setNombre("John Doe");

        when(clienteService.createCliente(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.clienteId").value("123"))
                .andExpect(jsonPath("$.nombre").value("John Doe"));

        verify(clienteService, times(1)).createCliente(any(Cliente.class));
    }

    @Test
    void testUpdateCliente() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
        Cliente cliente = new Cliente();
        cliente.setIdentificacion("123");
        cliente.setNombre("John Doe");

        when(clienteService.updateCliente(eq(1L), any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(put("/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.clienteId").value("123"))
                .andExpect(jsonPath("$.nombre").value("John Doe"));

        verify(clienteService, times(1)).updateCliente(eq(1L), any(Cliente.class));
    }

    @Test
    void testUpdateClienteNotFound() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();

        when(clienteService.updateCliente(eq(1L), any(Cliente.class))).thenReturn(null);

        mockMvc.perform(put("/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(new Cliente())))
                .andExpect(status().isNotFound());

        verify(clienteService, times(1)).updateCliente(eq(1L), any(Cliente.class));
    }

    @Test
    void testUpdateEstadoCliente() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
        Cliente cliente = new Cliente();
        cliente.setIdentificacion("123");
        cliente.setNombre("John Doe");

        when(clienteService.updateEstadoCliente(1L)).thenReturn(cliente);

        mockMvc.perform(patch("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.clienteId").value("123"))
                .andExpect(jsonPath("$.nombre").value("John Doe"));

        verify(clienteService, times(1)).updateEstadoCliente(1L);
    }

    @Test
    void testUpdateEstadoClienteNotFound() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();

        when(clienteService.updateEstadoCliente(1L)).thenReturn(null);

        mockMvc.perform(patch("/clientes/1"))
                .andExpect(status().isNotFound());

        verify(clienteService, times(1)).updateEstadoCliente(1L);
    }

    @Test
    void testDeleteCliente() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();

        when(clienteService.deleteCliente(1L)).thenReturn(true);

        mockMvc.perform(delete("/clientes/1"))
                .andExpect(status().isOk());

        verify(clienteService, times(1)).deleteCliente(1L);
    }

    @Test
    void testDeleteClienteNotFound() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();

        when(clienteService.deleteCliente(1L)).thenReturn(false);

        mockMvc.perform(delete("/clientes/1"))
                .andExpect(status().isNotFound());

        verify(clienteService, times(1)).deleteCliente(1L);
    }

}
