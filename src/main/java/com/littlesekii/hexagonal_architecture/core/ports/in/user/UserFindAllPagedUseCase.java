package com.littlesekii.hexagonal_architecture.core.ports.in.user;

import com.littlesekii.hexagonal_architecture.core.domain.User;
import com.littlesekii.hexagonal_architecture.core.wrapper.PageWrapper;

public interface UserFindAllPagedUseCase {
    PageWrapper<User> execute(Integer page, Integer size);
}
