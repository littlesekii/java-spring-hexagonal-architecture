package com.littlesekii.hexagonal_architecture.core.domain;

import java.util.Optional;

import com.littlesekii.hexagonal_architecture.core.exception.InvalidArgumentException;

public class User {
    
    private Long id;

    private String username;
    private String name;

    private Department department;

    public User() {}
    public User(
        Long id, 
        String username, 
        String name,
        Department department
    ) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.department = department;
    }

    public void validate() {
        if (username == null || username.isEmpty())
            throw new InvalidArgumentException("username cannot be blank");
        if (name == null || username.isEmpty())
            throw new InvalidArgumentException("name cannot be blank");
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
    public Optional<Department> getDepartment() {
        return Optional.ofNullable(department);
    }
    
    public void updateUsername(String username) {
        this.username = username;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateDepartment(Department department) {
        this.department = department;
    }

}
