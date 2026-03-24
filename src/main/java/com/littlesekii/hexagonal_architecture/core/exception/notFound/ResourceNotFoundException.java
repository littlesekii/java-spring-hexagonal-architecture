package com.littlesekii.hexagonal_architecture.core.exception.notFound;

public abstract class ResourceNotFoundException extends RuntimeException {

    private final String errorCode;

    public ResourceNotFoundException(String message, String errorCode) {
        this.errorCode = errorCode;
        super(message);
    }

    public String errorCode() {
        return errorCode;
    }
}
