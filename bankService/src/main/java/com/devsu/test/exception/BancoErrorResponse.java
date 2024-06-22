package com.devsu.test.exception;

public class BancoErrorResponse {

    private String message;

    public BancoErrorResponse() {}

    public BancoErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
