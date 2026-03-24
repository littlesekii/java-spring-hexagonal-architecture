package com.littlesekii.hexagonal_architecture.core.exception;

public class IntegrityViolationException extends RuntimeException {

    public final String errorCode = "DAT-001";

    public IntegrityViolationException(String message) {
        super(message);
    }   

    public String errorCode() {
        return errorCode;
    }
}
