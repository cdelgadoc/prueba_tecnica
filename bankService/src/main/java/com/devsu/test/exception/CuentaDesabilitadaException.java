package com.devsu.test.exception;

public class CuentaDesabilitadaException extends RuntimeException {

    public CuentaDesabilitadaException() {
    }

    public CuentaDesabilitadaException(String message) {
        super(message);
    }

    public CuentaDesabilitadaException(String message, Throwable cause) {
        super(message, cause);
    }

}