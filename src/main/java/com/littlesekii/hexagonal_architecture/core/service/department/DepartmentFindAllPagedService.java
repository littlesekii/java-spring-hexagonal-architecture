package com.littlesekii.hexagonal_architecture.core.service.department;

import com.littlesekii.hexagonal_architecture.core.domain.Department;
import com.littlesekii.hexagonal_architecture.core.exception.InvalidArgumentException;
import com.littlesekii.hexagonal_architecture.core.ports.in.department.DepartmentFindAllPagedUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.out.DepartmentRepositoryPort;
import com.littlesekii.hexagonal_architecture.core.wrapper.PageWrapper;

public class DepartmentFindAllPagedService implements DepartmentFindAllPagedUseCase {

    private final DepartmentRepositoryPort repositoryPort;

    public DepartmentFindAllPagedService(DepartmentRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public PageWrapper<Department> execute(Integer page, Integer size) {
        if (page == null)
            page = 0;
        if (size == null)
            size = 10;

        if (size <= 0) 
        throw new InvalidArgumentException("page size must be at least one");

        return repositoryPort.findAllPaged(page, size);
    }
    
}
