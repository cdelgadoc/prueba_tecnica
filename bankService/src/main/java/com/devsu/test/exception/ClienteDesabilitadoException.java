package com.devsu.test.exception;

public class ClienteDesabilitadoException extends RuntimeException {

    public ClienteDesabilitadoException() {
    }

    public ClienteDesabilitadoException(String message) {
        super(message);
    }

    public ClienteDesabilitadoException(String message, Throwable cause) {
        super(message, cause);
    }

}