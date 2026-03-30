package com.littlesekii.hexagonal_architecture.core.ports.out;

import java.util.List;
import java.util.Optional;

import com.littlesekii.hexagonal_architecture.core.domain.Department;
import com.littlesekii.hexagonal_architecture.core.wrapper.PageWrapper;

public interface DepartmentRepositoryPort {
    List<Department> findAll();
    PageWrapper<Department> findAllPaged(Integer page, Integer size);
    Optional<Department> findById(Long id);

    Department save(Department department);
    void deleteById(Long id);
    
    boolean existsByName(String name);
}
