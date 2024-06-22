package com.devsu.test.exception;

import java.util.List;

public class ClienteErrorResponse {

    private String message;
    private String validationError;
    private List<String> errors;

    public ClienteErrorResponse() {}

    public ClienteErrorResponse(String message) {
        this.message = message;
    }

    public ClienteErrorResponse(String validationError, List<String> errors) {
        this.validationError = validationError;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getValidationError() {
        return validationError;
    }

    public void setValidationError(String validationError) {
        this.validationError = validationError;
    }

}
