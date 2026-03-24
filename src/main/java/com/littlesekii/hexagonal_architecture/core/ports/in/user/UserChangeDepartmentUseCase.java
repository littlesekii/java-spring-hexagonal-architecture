package com.littlesekii.hexagonal_architecture.core.ports.in.user;

import com.littlesekii.hexagonal_architecture.core.domain.User;

public interface UserChangeDepartmentUseCase {
    User execute(Long userId, Long departmentId);
}
