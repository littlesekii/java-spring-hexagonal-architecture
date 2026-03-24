package com.littlesekii.hexagonal_architecture.core.service.department;

import com.littlesekii.hexagonal_architecture.core.domain.Department;
import com.littlesekii.hexagonal_architecture.core.ports.in.department.DepartmentCreateUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.out.DepartmentRepositoryPort;

public class DepartmentCreateService implements DepartmentCreateUseCase {

    private final DepartmentRepositoryPort repositoryPort;

    public DepartmentCreateService(DepartmentRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Department execute(Department data) {
        data.validate();

        if (repositoryPort.existsByName(data.getName()))
            throw new RuntimeException("a department with this name already exists");

        return repositoryPort.save(data);
    }
}
