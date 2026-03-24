package com.littlesekii.hexagonal_architecture.core.service.department;

import com.littlesekii.hexagonal_architecture.core.domain.Department;
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
            .orElseThrow(() -> new RuntimeException("department not found"));

        if (data.getName() != null)
            existing.updateName(data.getName());

        existing.validate();

        if (
            !existing.getName().equals(data.getName()) && 
            repositoryPort.existsByName(data.getName())
        ) {
            throw new RuntimeException("a department with this name already exists");
        }

        return repositoryPort.save(existing);
    }
}
