package com.devsu.test.utils;

import com.devsu.test.dto.ClienteDTO;
import com.devsu.test.model.Cliente;

public class ClienteUtil {

    public static ClienteDTO getClienteDTO(Cliente entity) {
        ClienteDTO clienteDTO = null;

        if (entity != null) {
            clienteDTO = new ClienteDTO();
            clienteDTO.setClienteId(entity.getClienteId());
            clienteDTO.setNombre(entity.getNombre());
            clienteDTO.setEstado(entity.getEstado());
        }

        return clienteDTO;
    }

}
