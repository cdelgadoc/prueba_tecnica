package com.devsu.test.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BancoRestExceptionHandler {

    @Value("${error.messagge}")
    private String errorMessage;

    @Value("${messagge.cliente.notfound}")
    private String clienteNotFound;

    @Value("${messagge.cuenta.notfound}")
    private String cuentaNotFound;

    @Value("${messagge.saldo.insuficiente}")
    private String saldoInsuficiente;

    @Value("${messagge.cliente.desactivado}")
    private String clienteDesactivado;

    @Value("${messagge.cuenta.desactivada}")
    private String cuentaDesactivada;

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<BancoErrorResponse> handleClienteNotFoundException(ClienteNotFoundException ex) {
        BancoErrorResponse error = new BancoErrorResponse(clienteNotFound);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CuentaNotFoundException.class)
    public ResponseEntity<BancoErrorResponse> handleCuentaNotFoundException(CuentaNotFoundException ex) {
        BancoErrorResponse error = new BancoErrorResponse(cuentaNotFound);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<BancoErrorResponse> handleSaldoInsuficienteException(SaldoInsuficienteException ex) {
        BancoErrorResponse error = new BancoErrorResponse(saldoInsuficiente);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClienteDesabilitadoException.class)
    public ResponseEntity<BancoErrorResponse> handleClienteDesabilitadoException(ClienteDesabilitadoException ex) {
        BancoErrorResponse error = new BancoErrorResponse(clienteDesactivado);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CuentaDesabilitadaException.class)
    public ResponseEntity<BancoErrorResponse> handleCuentaDesabilitadoException(CuentaDesabilitadaException ex) {
        BancoErrorResponse error = new BancoErrorResponse(cuentaDesactivada);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BancoErrorResponse> handleException(Exception exception) {
        BancoErrorResponse error = new BancoErrorResponse(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
