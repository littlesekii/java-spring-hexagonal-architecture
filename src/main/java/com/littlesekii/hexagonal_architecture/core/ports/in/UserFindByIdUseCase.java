package com.littlesekii.hexagonal_architecture.core.ports.in;

import com.littlesekii.hexagonal_architecture.core.domain.User;

public interface UserFindByIdUseCase {
    User execute(Long id);
}
