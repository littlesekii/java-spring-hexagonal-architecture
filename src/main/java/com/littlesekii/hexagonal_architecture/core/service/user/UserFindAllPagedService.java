package com.littlesekii.hexagonal_architecture.core.service.user;

import com.littlesekii.hexagonal_architecture.core.domain.User;
import com.littlesekii.hexagonal_architecture.core.ports.in.user.UserFindAllPagedUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.out.UserRepositoryPort;
import com.littlesekii.hexagonal_architecture.core.wrapper.PageWrapper;

public class UserFindAllPagedService implements UserFindAllPagedUseCase {

    private final UserRepositoryPort repositoryPort;

    public UserFindAllPagedService(UserRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public PageWrapper<User> execute(Integer page, Integer size) {
        if (page == null)
            page = 0;
        if (size == null)
            size = 10;
        
        return repositoryPort.findAllPaged(page, size);
    }
    
}
