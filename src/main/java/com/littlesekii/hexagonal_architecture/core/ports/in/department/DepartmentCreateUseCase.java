package com.littlesekii.hexagonal_architecture.core.ports.in.department;

import com.littlesekii.hexagonal_architecture.core.domain.Department;

public interface DepartmentCreateUseCase {
    Department execute(Department department);
}
