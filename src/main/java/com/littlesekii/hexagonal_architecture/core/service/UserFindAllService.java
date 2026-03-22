package com.littlesekii.hexagonal_architecture.core.service;

import java.util.List;

import com.littlesekii.hexagonal_architecture.core.domain.User;
import com.littlesekii.hexagonal_architecture.core.ports.in.UserFindAllUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.out.UserRepositoryPort;

public class UserFindAllService implements UserFindAllUseCase {

    private final UserRepositoryPort repositoryPort;

    public UserFindAllService(UserRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public List<User> execute() {
        return repositoryPort.findAll();
    }
    
}
