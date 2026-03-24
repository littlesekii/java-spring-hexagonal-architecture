package com.littlesekii.hexagonal_architecture.core.service.user;

import com.littlesekii.hexagonal_architecture.core.domain.User;
import com.littlesekii.hexagonal_architecture.core.exception.IntegrityViolationException;
import com.littlesekii.hexagonal_architecture.core.exception.notFound.UserNotFoundException;
import com.littlesekii.hexagonal_architecture.core.ports.in.user.UserUpdateUseCase;
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
            .orElseThrow(() -> new UserNotFoundException());

        if (
            !existing.getUsername().equals(data.getUsername()) && 
            repositoryPort.existsByUsername(data.getUsername())
        ) {
            throw new IntegrityViolationException("this username is already taken");
        }

        existing.updateUsername(data.getUsername());
        existing.updateName(data.getName());

        return repositoryPort.save(existing);
    }
}
