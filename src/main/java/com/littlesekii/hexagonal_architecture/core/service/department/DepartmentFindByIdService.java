package com.littlesekii.hexagonal_architecture.core.service.department;

import com.littlesekii.hexagonal_architecture.core.domain.Department;
import com.littlesekii.hexagonal_architecture.core.exception.notFound.DepartmentNotFoundException;
import com.littlesekii.hexagonal_architecture.core.ports.in.department.DepartmentFindByIdUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.out.DepartmentRepositoryPort;

public class DepartmentFindByIdService implements DepartmentFindByIdUseCase {

    private final DepartmentRepositoryPort repositoryPort;

    public DepartmentFindByIdService(DepartmentRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Department execute(Long id) {
        return repositoryPort.findById(id)
            .orElseThrow(() -> new DepartmentNotFoundException());
    }
    
}
