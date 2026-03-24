package com.littlesekii.hexagonal_architecture.core.service.department;

import com.littlesekii.hexagonal_architecture.core.domain.Department;
import com.littlesekii.hexagonal_architecture.core.exception.IntegrityViolationException;
import com.littlesekii.hexagonal_architecture.core.exception.notFound.DepartmentNotFoundException;
import com.littlesekii.hexagonal_architecture.core.ports.in.department.DepartmentUpdateUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.out.DepartmentRepositoryPort;

public class DepartmentUpdateService implements DepartmentUpdateUseCase {

    private final DepartmentRepositoryPort repositoryPort;

    public DepartmentUpdateService(DepartmentRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Department execute(Long id, Department data) {
        data.validate();

        Department existing = repositoryPort.findById(id)
            .orElseThrow(() -> new DepartmentNotFoundException());

        if (
            !existing.getName().equals(data.getName()) && 
            repositoryPort.existsByName(data.getName())
        ) {
            throw new IntegrityViolationException("a department with this name already exists");
        }

        existing.updateName(data.getName());

        return repositoryPort.save(existing);
    }
}
