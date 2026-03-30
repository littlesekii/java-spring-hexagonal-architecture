package com.littlesekii.hexagonal_architecture.adapters.in.web.dto;

public record PageRequest (
    Integer page,
    Integer size
) {}