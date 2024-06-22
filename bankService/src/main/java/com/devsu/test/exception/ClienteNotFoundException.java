package com.devsu.test.exception;

public class ClienteNotFoundException extends RuntimeException {

    public ClienteNotFoundException() {
    }

    public ClienteNotFoundException(String message) {
        super(message);
    }

    public ClienteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
