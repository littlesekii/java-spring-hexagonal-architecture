package com.littlesekii.hexagonal_architecture.core.exception;

public class InvalidArgumentException extends RuntimeException {

    public final String errorCode = "VAL-001";

    public InvalidArgumentException(String message) {
        super(message);
    }

    public String errorCode() {
        return errorCode;
    }
}
