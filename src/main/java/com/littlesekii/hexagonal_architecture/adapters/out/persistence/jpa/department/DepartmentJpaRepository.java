package com.littlesekii.hexagonal_architecture.adapters.out.persistence.jpa.department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentJpaRepository extends JpaRepository<DepartmentJpaEntity, Long> {
    boolean existsByName(String name);
}
