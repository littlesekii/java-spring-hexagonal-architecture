package com.littlesekii.hexagonal_architecture.adapters.out.persistence.jpa.user;

import java.util.Optional;

import com.littlesekii.hexagonal_architecture.adapters.out.persistence.jpa.department.DepartmentJpaEntity;
import com.littlesekii.hexagonal_architecture.core.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserJpaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentJpaEntity department;

    public UserJpaEntity() {}
    public UserJpaEntity(
        Long id,
        String username, 
        String name,
        DepartmentJpaEntity department
    ) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.department = department;
    }

    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getName() {
        return name;
    }
    public DepartmentJpaEntity getDepartment() {
        return department;
    }
    
    public static UserJpaEntity fromDomain(User domain) {
        return new UserJpaEntity(
            domain.getId(),
            domain.getUsername(), 
            domain.getName(),
            domain.getDepartment()
                .map(DepartmentJpaEntity::fromDomain)
                .orElse(null)
        );
    }
    public User toDomain() {
        return new User(
            id, 
            username, 
            name, 
            Optional.ofNullable(department)
                .map(DepartmentJpaEntity::toDomain)
                .orElse(null)
        );
    }
}
