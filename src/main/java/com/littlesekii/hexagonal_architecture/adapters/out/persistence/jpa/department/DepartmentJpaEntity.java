package com.littlesekii.hexagonal_architecture.adapters.out.persistence.jpa.department;

import java.util.Set;

import com.littlesekii.hexagonal_architecture.adapters.out.persistence.jpa.user.UserJpaEntity;
import com.littlesekii.hexagonal_architecture.core.domain.Department;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class DepartmentJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<UserJpaEntity> users;

    public DepartmentJpaEntity() {}
    public DepartmentJpaEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Set<UserJpaEntity> getUsers() {
        return users;
    }   
    
    public static DepartmentJpaEntity fromDomain(Department domain) {
        return new DepartmentJpaEntity(
            domain.getId(),
            domain.getName()
        );
    }
    public Department toDomain() {
        return new Department(id, name);
    }
}
