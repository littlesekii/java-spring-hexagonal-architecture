package com.littlesekii.hexagonal_architecture.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.littlesekii.hexagonal_architecture.core.ports.out.DepartmentRepositoryPort;
import com.littlesekii.hexagonal_architecture.core.service.department.DepartmentCreateService;
import com.littlesekii.hexagonal_architecture.core.service.department.DepartmentDeleteService;
import com.littlesekii.hexagonal_architecture.core.service.department.DepartmentFindAllPagedService;
import com.littlesekii.hexagonal_architecture.core.service.department.DepartmentFindAllService;
import com.littlesekii.hexagonal_architecture.core.service.department.DepartmentFindByIdService;
import com.littlesekii.hexagonal_architecture.core.service.department.DepartmentPartialUpdateService;
import com.littlesekii.hexagonal_architecture.core.service.department.DepartmentUpdateService;

@Configuration
@EnableTransactionManagement
public class DepartmentBeanConfig {

    @Bean
    public DepartmentFindAllService DepartmentFindAllService(DepartmentRepositoryPort repositoryPort) {
        return new DepartmentFindAllService(repositoryPort);
    }

    @Bean
    public DepartmentFindAllPagedService DepartmentFindAllPagedService(DepartmentRepositoryPort repositoryPort) {
        return new DepartmentFindAllPagedService(repositoryPort);
    }

    @Bean
    public DepartmentFindByIdService DepartmentFindByIdIdService(DepartmentRepositoryPort repositoryPort) {
        return new DepartmentFindByIdService(repositoryPort);
    }

    @Bean
    @Transactional
    public DepartmentCreateService DepartmentCreateService(DepartmentRepositoryPort repositoryPort) {
        return new DepartmentCreateService(repositoryPort);
    }

    @Bean
    @Transactional
    public DepartmentUpdateService DepartmentUpdateService(DepartmentRepositoryPort repositoryPort) {
        return new DepartmentUpdateService(repositoryPort);
    }

    @Bean
    @Transactional
    public DepartmentPartialUpdateService DepartmentPartialUpdateService(DepartmentRepositoryPort repositoryPort) {
        return new DepartmentPartialUpdateService(repositoryPort);
    }

    @Bean
    @Transactional
    public DepartmentDeleteService DepartmentDeleteService(DepartmentRepositoryPort repositoryPort) {
        return new DepartmentDeleteService(repositoryPort);
    }
}