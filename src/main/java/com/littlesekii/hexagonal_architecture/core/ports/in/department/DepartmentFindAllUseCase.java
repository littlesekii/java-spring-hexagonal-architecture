package com.littlesekii.hexagonal_architecture.core.ports.in.department;

import java.util.List;

import com.littlesekii.hexagonal_architecture.core.domain.Department;

public interface DepartmentFindAllUseCase {
    List<Department> execute();
}
