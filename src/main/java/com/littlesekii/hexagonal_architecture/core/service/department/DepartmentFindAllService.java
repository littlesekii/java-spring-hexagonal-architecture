package com.littlesekii.hexagonal_architecture.core.service.department;

import java.util.List;

import com.littlesekii.hexagonal_architecture.core.domain.Department;
import com.littlesekii.hexagonal_architecture.core.ports.in.department.DepartmentFindAllUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.out.DepartmentRepositoryPort;

public class DepartmentFindAllService implements DepartmentFindAllUseCase {

    private final DepartmentRepositoryPort repositoryPort;

    public DepartmentFindAllService(DepartmentRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public List<Department> execute() {
        return repositoryPort.findAll();
    }
    
}
