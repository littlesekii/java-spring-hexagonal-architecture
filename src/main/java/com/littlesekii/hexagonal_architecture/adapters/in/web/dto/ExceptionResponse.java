package com.littlesekii.hexagonal_architecture.adapters.in.web.dto;

import java.time.Instant;

public record ExceptionResponse (
    Instant moment,
    Integer statusCode,
    String error,
    String errorCode,
    String message,
    String path
) {}
