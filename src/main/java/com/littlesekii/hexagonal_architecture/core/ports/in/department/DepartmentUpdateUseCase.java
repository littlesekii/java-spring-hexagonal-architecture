package com.littlesekii.hexagonal_architecture.core.ports.in.department;

import com.littlesekii.hexagonal_architecture.core.domain.Department;

public interface DepartmentUpdateUseCase {
    Department execute(Long id, Department user);
}
