package com.devsu.test.exception;

public class SaldoInsuficienteException extends RuntimeException {

    public SaldoInsuficienteException() {}

    public SaldoInsuficienteException(String message) {
        super(message);
    }

}
