package com.littlesekii.hexagonal_architecture.core.ports.out;

import java.util.List;
import java.util.Optional;

import com.littlesekii.hexagonal_architecture.core.domain.User;
import com.littlesekii.hexagonal_architecture.core.wrapper.PageWrapper;

public interface UserRepositoryPort {
    List<User> findAll();
    PageWrapper<User> findAllPaged(Integer page, Integer size);
    Optional<User> findById(Long id);
    
    User save(User user);
    void deleteById(Long id);

    boolean existsByUsername(String username);
}
