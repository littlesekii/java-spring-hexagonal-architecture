package com.littlesekii.hexagonal_architecture.adapters.out.persistence.jpa.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.littlesekii.hexagonal_architecture.core.domain.User;
import com.littlesekii.hexagonal_architecture.core.ports.out.UserRepositoryPort;

@Component
public class UserJpaAdapter implements UserRepositoryPort {

    private final UserJpaRepository repository;

    public UserJpaAdapter(UserJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll().stream()
            .map(UserJpaEntity::toDomain)
            .toList();
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<UserJpaEntity> entity = repository.findById(id);
        return entity.map(UserJpaEntity::toDomain);
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = UserJpaEntity.fromDomain(user);
        UserJpaEntity saved = repository.save(entity);
        return saved.toDomain();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }   
}
