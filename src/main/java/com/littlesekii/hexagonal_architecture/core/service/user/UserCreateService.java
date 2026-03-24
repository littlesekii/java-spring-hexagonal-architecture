package com.littlesekii.hexagonal_architecture.core.service.user;

import com.littlesekii.hexagonal_architecture.core.domain.User;
import com.littlesekii.hexagonal_architecture.core.exception.IntegrityViolationException;
import com.littlesekii.hexagonal_architecture.core.ports.in.user.UserCreateUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.out.UserRepositoryPort;

public class UserCreateService implements UserCreateUseCase {

    private final UserRepositoryPort repositoryPort;

    public UserCreateService(UserRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public User execute(User data) {
        data.validate();

        if (repositoryPort.existsByUsername(data.getUsername()))
            throw new IntegrityViolationException("this username is already taken");

        return repositoryPort.save(data);
    }
    
}
