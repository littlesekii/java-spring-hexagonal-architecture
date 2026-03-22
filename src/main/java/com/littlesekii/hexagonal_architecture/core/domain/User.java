package com.littlesekii.hexagonal_architecture.core.domain;

public class User {
    
    private Long id;

    private String username;
    private String name;

    public User() {}
    public User(
        Long id, 
        String username, 
        String name
    ) {
        this.id = id;
        this.username = username;
        this.name = name;
    }

    public void validate() {
        if (username == null || username.isEmpty())
            throw new IllegalArgumentException("username cannot be blank");
        if (name == null || username.isEmpty())
            throw new IllegalArgumentException("name cannot be blank");
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

    public void updateUsername(String username) {
        this.username = username;
    }

    public void updateName(String name) {
        this.name = name;
    }

}
