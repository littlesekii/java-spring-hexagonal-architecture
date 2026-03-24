package com.littlesekii.hexagonal_architecture.core.exception.notFound;

public class DepartmentNotFoundException extends ResourceNotFoundException {

    private static final String errorCode = "DEP-404";

    public DepartmentNotFoundException() {
        super("department not found", errorCode);
    }

    public DepartmentNotFoundException(String message) {
        super(message, errorCode);
    }
}
