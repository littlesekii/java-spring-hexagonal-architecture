package com.littlesekii.hexagonal_architecture.adapters.in.web.dto.department;

import com.littlesekii.hexagonal_architecture.core.domain.Department;

public record DepartmentCreateRequest(String name) {
    public Department toDomain() {
        return new Department(
            null,
            name
        );
    }
}
