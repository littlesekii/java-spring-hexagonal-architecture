package com.littlesekii.hexagonal_architecture.core.ports.in.department;

import com.littlesekii.hexagonal_architecture.core.domain.Department;
import com.littlesekii.hexagonal_architecture.core.wrapper.PageWrapper;

public interface DepartmentFindAllPagedUseCase {
    PageWrapper<Department> execute(Integer page, Integer size);
}
