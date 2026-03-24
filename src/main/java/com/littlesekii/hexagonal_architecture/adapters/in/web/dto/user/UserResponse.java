package com.littlesekii.hexagonal_architecture.adapters.in.web.dto.user;

import com.littlesekii.hexagonal_architecture.adapters.in.web.dto.department.DepartmentResponse;
import com.littlesekii.hexagonal_architecture.core.domain.User;

public record UserResponse(Long id, String username, String name, DepartmentResponse department) {
    public static UserResponse fromDomain(User domain) {
        return new UserResponse(
            domain.getId(), 
            domain.getUsername(), 
            domain.getName(),
            domain.getDepartment()
                .map(DepartmentResponse::fromDomain)
                .orElse(null)
        );
    }
}
