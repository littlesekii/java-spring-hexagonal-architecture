package com.littlesekii.hexagonal_architecture.core.service.department;

import com.littlesekii.hexagonal_architecture.core.exception.notFound.DepartmentNotFoundException;
import com.littlesekii.hexagonal_architecture.core.ports.in.department.DepartmentDeleteUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.out.DepartmentRepositoryPort;

public class DepartmentDeleteService implements DepartmentDeleteUseCase {

    private final DepartmentRepositoryPort repositoryPort;

    public DepartmentDeleteService(DepartmentRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public void execute(Long id) {
        repositoryPort.findById(id)
            .orElseThrow(() -> new DepartmentNotFoundException());

        repositoryPort.deleteById(id);
    }
    
}
