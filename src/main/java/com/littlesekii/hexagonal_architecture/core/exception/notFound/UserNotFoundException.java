package com.littlesekii.hexagonal_architecture.core.exception.notFound;

public class UserNotFoundException extends ResourceNotFoundException {

    public static final String errorCode = "USR-404";

    public UserNotFoundException() {
        super("user not found", errorCode);
    }

    public UserNotFoundException(String message) {
        super(message, errorCode);
    }
}
