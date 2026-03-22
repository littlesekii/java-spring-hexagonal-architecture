package com.littlesekii.hexagonal_architecture.core.service;

import com.littlesekii.hexagonal_architecture.core.domain.User;
import com.littlesekii.hexagonal_architecture.core.ports.in.UserUpdateUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.out.UserRepositoryPort;

public class UserUpdateService implements UserUpdateUseCase {

    private final UserRepositoryPort repositoryPort;

    public UserUpdateService(UserRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public User execute(Long id, User data) {
        data.validate();

        User existing = repositoryPort.findById(id)
            .orElseThrow(() -> new RuntimeException("user not found"));

        if (
            !existing.getUsername().equals(data.getUsername()) && 
            repositoryPort.existsByUsername(data.getUsername())
        ) {
            throw new RuntimeException("this username is already taken");
        }

        existing.updateUsername(data.getUsername());
        existing.updateName(data.getName());

        return repositoryPort.save(existing);
    }
}
