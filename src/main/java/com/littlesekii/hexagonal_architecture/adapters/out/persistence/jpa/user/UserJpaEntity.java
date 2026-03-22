package com.littlesekii.hexagonal_architecture.adapters.out.persistence.jpa.user;

import com.littlesekii.hexagonal_architecture.core.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    public UserJpaEntity() {}
    public UserJpaEntity(
        Long id,
        String username, 
        String name
    ) {
        this.id = id;
        this.username = username;
        this.name = name;
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
    
    public static UserJpaEntity fromDomain(User user) {
        return new UserJpaEntity(
            user.getId(),
            user.getUsername(), 
            user.getName()
        );
    }
    public User toDomain() {
        return new User(id, username, name);
    }
}
