package com.littlesekii.hexagonal_architecture.infra.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.littlesekii.hexagonal_architecture.adapters.in.web.dto.ExceptionResponse;
import com.littlesekii.hexagonal_architecture.core.exception.IntegrityViolationException;
import com.littlesekii.hexagonal_architecture.core.exception.InvalidArgumentException;
import com.littlesekii.hexagonal_architecture.core.exception.notFound.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<ExceptionResponse> invalidArgument(InvalidArgumentException e, HttpServletRequest request) {
        ExceptionResponse res = new ExceptionResponse(
            Instant.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Invalid argument",
            e.errorCode(),
            e.getMessage(),
            request.getRequestURI()
        ); 

        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(IntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> integrityViolation(IntegrityViolationException e, HttpServletRequest request) {
        ExceptionResponse res = new ExceptionResponse(
            Instant.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Integrity violation",
            e.errorCode(),
            e.getMessage(),
            request.getRequestURI()
        ); 

        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        ExceptionResponse res = new ExceptionResponse(
            Instant.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Resource not found",
            e.errorCode(),
            e.getMessage(),
            request.getRequestURI()
        ); 

        return ResponseEntity.badRequest().body(res);
    }
}
