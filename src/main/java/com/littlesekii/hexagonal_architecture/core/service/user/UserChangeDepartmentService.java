package com.littlesekii.hexagonal_architecture.core.service.user;

import com.littlesekii.hexagonal_architecture.core.domain.Department;
import com.littlesekii.hexagonal_architecture.core.domain.User;
import com.littlesekii.hexagonal_architecture.core.exception.InvalidArgumentException;
import com.littlesekii.hexagonal_architecture.core.exception.notFound.DepartmentNotFoundException;
import com.littlesekii.hexagonal_architecture.core.exception.notFound.UserNotFoundException;
import com.littlesekii.hexagonal_architecture.core.ports.in.user.UserChangeDepartmentUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.out.DepartmentRepositoryPort;
import com.littlesekii.hexagonal_architecture.core.ports.out.UserRepositoryPort;

public class UserChangeDepartmentService implements UserChangeDepartmentUseCase {

    private final UserRepositoryPort repositoryPort;
    private final DepartmentRepositoryPort departmentRepositoryPort;

    public UserChangeDepartmentService(
        UserRepositoryPort repositoryPort,
        DepartmentRepositoryPort departmentRepositoryPort
    ) {
        this.repositoryPort = repositoryPort;
        this.departmentRepositoryPort = departmentRepositoryPort;
    }

    @Override
    public User execute(Long userId, Long departmentId) {

        User user = repositoryPort.findById(userId)
            .orElseThrow(() -> new UserNotFoundException());
    
        if (departmentId == null)
            throw new InvalidArgumentException("departmentId cannot be null");

        Department department = departmentRepositoryPort.findById(departmentId)
            .orElseThrow(() -> new DepartmentNotFoundException());

        user.updateDepartment(department);

        return repositoryPort.save(user);
    }
    
}
