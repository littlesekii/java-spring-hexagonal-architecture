package com.littlesekii.hexagonal_architecture.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.littlesekii.hexagonal_architecture.core.ports.out.UserRepositoryPort;
import com.littlesekii.hexagonal_architecture.core.service.UserCreateService;
import com.littlesekii.hexagonal_architecture.core.service.UserDeleteService;
import com.littlesekii.hexagonal_architecture.core.service.UserFindAllService;
import com.littlesekii.hexagonal_architecture.core.service.UserFindByIdService;
import com.littlesekii.hexagonal_architecture.core.service.UserPartialUpdateService;
import com.littlesekii.hexagonal_architecture.core.service.UserUpdateService;

@Configuration
@EnableTransactionManagement
public class BeanConfig {
    

    @Bean
    public UserFindAllService userFindAllService(UserRepositoryPort repositoryPort) {
        return new UserFindAllService(repositoryPort);
    }

    @Bean
    public UserFindByIdService userFindByIdIdService(UserRepositoryPort repositoryPort) {
        return new UserFindByIdService(repositoryPort);
    }

    @Bean
    @Transactional
    public UserCreateService userCreateService(UserRepositoryPort repositoryPort) {
        return new UserCreateService(repositoryPort);
    }

    @Bean
    @Transactional
    public UserUpdateService userUpdateService(UserRepositoryPort repositoryPort) {
        return new UserUpdateService(repositoryPort);
    }

    @Bean
    @Transactional
    public UserPartialUpdateService userPartialUpdateService(UserRepositoryPort repositoryPort) {
        return new UserPartialUpdateService(repositoryPort);
    }

    @Bean
    @Transactional
    public UserDeleteService userDeleteService(UserRepositoryPort repositoryPort) {
        return new UserDeleteService(repositoryPort);
    }
}
