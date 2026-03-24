package com.littlesekii.hexagonal_architecture.adapters.in.web.dto.user;

import com.littlesekii.hexagonal_architecture.core.domain.User;

public record UserUpdateRequest(String username, String name) {
    public User toDomain() {
        return new User(
            null, 
            username,
            name,
            null
        );
    }
}