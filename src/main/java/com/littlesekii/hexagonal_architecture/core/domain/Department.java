package com.littlesekii.hexagonal_architecture.core.domain;

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
            throw new IllegalArgumentException("name cannot be blank");
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