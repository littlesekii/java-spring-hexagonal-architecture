package com.littlesekii.hexagonal_architecture.core.service.department;

import com.littlesekii.hexagonal_architecture.core.domain.Department;
import com.littlesekii.hexagonal_architecture.core.exception.IntegrityViolationException;
import com.littlesekii.hexagonal_architecture.core.exception.notFound.DepartmentNotFoundException;
import com.littlesekii.hexagonal_architecture.core.ports.in.department.DepartmentPartialUpdateUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.out.DepartmentRepositoryPort;

public class DepartmentPartialUpdateService implements DepartmentPartialUpdateUseCase {

    private final DepartmentRepositoryPort repositoryPort;

    public DepartmentPartialUpdateService(DepartmentRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Department execute(Long id, Department data) {       

        Department existing = repositoryPort.findById(id)
            .orElseThrow(() -> new DepartmentNotFoundException());

        if (
            !existing.getName().equals(data.getName()) && 
            repositoryPort.existsByName(data.getName())
        ) {
            throw new IntegrityViolationException("a department with this name already exists");
        }

        if (data.getName() != null)
            existing.updateName(data.getName());

        existing.validate();

        return repositoryPort.save(existing);
    }
}
