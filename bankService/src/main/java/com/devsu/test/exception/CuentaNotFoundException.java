package com.devsu.test.exception;

public class CuentaNotFoundException extends RuntimeException {

    public CuentaNotFoundException() {
    }

    public CuentaNotFoundException(String message) {
        super(message);
    }

    public CuentaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}


