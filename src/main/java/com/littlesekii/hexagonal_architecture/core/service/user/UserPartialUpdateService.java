package com.littlesekii.hexagonal_architecture.core.service.user;

import com.littlesekii.hexagonal_architecture.core.domain.User;
import com.littlesekii.hexagonal_architecture.core.ports.in.user.UserPartialUpdateUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.out.UserRepositoryPort;

public class UserPartialUpdateService implements UserPartialUpdateUseCase {

    private final UserRepositoryPort repositoryPort;

    public UserPartialUpdateService(UserRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public User execute(Long id, User data) {       

        User existing = repositoryPort.findById(id)
            .orElseThrow(() -> new RuntimeException("user not found"));

        if (data.getUsername() != null)
            existing.updateUsername(data.getUsername());

        if (data.getName() != null)
            existing.updateName(data.getName());

        existing.validate();

        if (
            !existing.getUsername().equals(data.getUsername()) && 
            repositoryPort.existsByUsername(data.getUsername())
        ) {
            throw new RuntimeException("this username is already taken");
        }

        return repositoryPort.save(existing);
    }
}
