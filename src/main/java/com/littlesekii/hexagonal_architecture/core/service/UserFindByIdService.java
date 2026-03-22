package com.littlesekii.hexagonal_architecture.core.service;

import com.littlesekii.hexagonal_architecture.core.domain.User;
import com.littlesekii.hexagonal_architecture.core.ports.in.UserFindByIdUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.out.UserRepositoryPort;

public class UserFindByIdService implements UserFindByIdUseCase {

    private final UserRepositoryPort repositoryPort;

    public UserFindByIdService(UserRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public User execute(Long id) {
        return repositoryPort.findById(id)
            .orElseThrow(() -> new RuntimeException("user not found"));
    }
    
}
