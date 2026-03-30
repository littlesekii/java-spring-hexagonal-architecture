package com.littlesekii.hexagonal_architecture.core.wrapper;

import java.util.List;

public record PageWrapper<T> (
    List<T> content,
    int page,
    int size,
    long totalElements,
    int totalPages
) {}
