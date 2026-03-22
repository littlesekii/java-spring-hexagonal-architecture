package com.littlesekii.hexagonal_architecture.core.ports.out;

import java.util.List;
import java.util.Optional;

import com.littlesekii.hexagonal_architecture.core.domain.User;

public interface UserRepositoryPort {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    void deleteById(Long id);

    boolean existsByUsername(String username);
}
