package com.littlesekii.hexagonal_architecture.adapters.out.persistence.jpa.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

import com.littlesekii.hexagonal_architecture.core.domain.User;
import com.littlesekii.hexagonal_architecture.core.ports.out.UserRepositoryPort;
import com.littlesekii.hexagonal_architecture.core.wrapper.PageWrapper;

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
    public PageWrapper<User> findAllPaged(Integer page, Integer size) {        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Order.asc("id")));
        Page<UserJpaEntity> entityPage = repository.findAll(pageable);

        return new PageWrapper<>(
            entityPage.getContent().stream()
                .map(UserJpaEntity::toDomain)
                .toList(), 
            entityPage.getNumber(), 
            entityPage.getSize(), 
            entityPage.getTotalElements(), 
            entityPage.getTotalPages()
            );
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
