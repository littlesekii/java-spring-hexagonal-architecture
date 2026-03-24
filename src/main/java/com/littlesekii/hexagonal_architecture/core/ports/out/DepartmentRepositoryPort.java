package com.littlesekii.hexagonal_architecture.core.ports.out;

import java.util.List;
import java.util.Optional;

import com.littlesekii.hexagonal_architecture.core.domain.Department;

public interface DepartmentRepositoryPort {
    List<Department> findAll();
    Optional<Department> findById(Long id);
    Department save(Department department);
    void deleteById(Long id);
    
    boolean existsByName(String name);
}
