package com.littlesekii.hexagonal_architecture.adapters.in.web.dto.user;

import com.littlesekii.hexagonal_architecture.core.domain.User;

public record UserResponse(Long id, String username, String name) {
    public static UserResponse fromDomain(User user) {
        return new UserResponse(
            user.getId(), 
            user.getUsername(), 
            user.getName()
        );
    }
}
