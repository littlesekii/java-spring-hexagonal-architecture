package com.littlesekii.hexagonal_architecture.core.domain;

import com.littlesekii.hexagonal_architecture.core.exception.InvalidArgumentException;

public class Department {

    private Long id;
    private String name;

    public Department() {}
    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void validate() {
        if (name == null || name.isEmpty())
            throw new InvalidArgumentException("name cannot be blank");
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void updateName(String name) {
        this.name = name;
    }
}