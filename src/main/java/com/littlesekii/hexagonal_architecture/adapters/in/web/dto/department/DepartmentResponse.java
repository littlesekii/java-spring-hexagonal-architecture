package com.littlesekii.hexagonal_architecture.adapters.in.web.dto.department;

import com.littlesekii.hexagonal_architecture.core.domain.Department;

public record DepartmentResponse(Long id, String name) {
    public static DepartmentResponse fromDomain(Department domain) {
        return new DepartmentResponse(
            domain.getId(),
            domain.getName()
        );
    }

}
