package com.littlesekii.hexagonal_architecture.core.ports.in.user;

import java.util.List;

import com.littlesekii.hexagonal_architecture.core.domain.User;

public interface UserFindAllUseCase {
    List<User> execute();
}
