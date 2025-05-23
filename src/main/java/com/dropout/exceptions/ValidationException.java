package com.dropout.exceptions;

import java.util.Map;

public class ValidationException extends RuntimeException {
    private Map<String, Object> errors;

    public ValidationException(String message, Map<String, Object> errors) {
        super(message);
        this.errors = errors;
    }

    public Map<String, Object> getErrors() {
        return errors;
    }
}
