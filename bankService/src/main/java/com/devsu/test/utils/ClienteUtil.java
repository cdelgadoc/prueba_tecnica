package com.devsu.test.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.devsu.test.model.Cliente;

public class ClienteUtil {

    private static Pattern pattern = Pattern.compile("clienteId='(\\d+)', nombre='([^']+)', estado=(true|false)");

    public static Cliente getCliente(String text) {
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            String clienteId = matcher.group(1);
            String nombre = matcher.group(2);
            Boolean estado = Boolean.valueOf(matcher.group(3));
            Cliente cliente = new Cliente();
            cliente.setClienteId(clienteId);
            cliente.setNombre(nombre);
            cliente.setEstado(estado);
            return cliente;
        }
        return null;
    }
}
