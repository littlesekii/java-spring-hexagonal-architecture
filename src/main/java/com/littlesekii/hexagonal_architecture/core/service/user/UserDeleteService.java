package com.littlesekii.hexagonal_architecture.core.service.user;

import com.littlesekii.hexagonal_architecture.core.ports.in.user.UserDeleteUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.out.UserRepositoryPort;

public class UserDeleteService implements UserDeleteUseCase {

    private final UserRepositoryPort repositoryPort;

    public UserDeleteService(UserRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public void execute(Long id) {
        repositoryPort.findById(id)
            .orElseThrow(() -> new RuntimeException("user not found"));

        repositoryPort.deleteById(id);
    }
    
}
