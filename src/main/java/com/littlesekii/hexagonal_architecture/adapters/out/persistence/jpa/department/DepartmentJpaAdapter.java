package com.littlesekii.hexagonal_architecture.adapters.out.persistence.jpa.department;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.littlesekii.hexagonal_architecture.core.domain.Department;
import com.littlesekii.hexagonal_architecture.core.ports.out.DepartmentRepositoryPort;

@Component
public class DepartmentJpaAdapter implements DepartmentRepositoryPort {

    private final DepartmentJpaRepository repository;

    public DepartmentJpaAdapter(DepartmentJpaRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public List<Department> findAll() {
        return repository.findAll().stream()
            .map(DepartmentJpaEntity::toDomain)
            .toList();
    }

    @Override
    public Optional<Department> findById(Long id) {
        Optional<DepartmentJpaEntity> entity = repository.findById(id);
        return entity.map(DepartmentJpaEntity::toDomain);
    }

    @Override
    public Department save(Department department) {
        DepartmentJpaEntity entity = DepartmentJpaEntity.fromDomain(department);
        DepartmentJpaEntity saved = repository.save(entity);
        return saved.toDomain();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    } 
}
