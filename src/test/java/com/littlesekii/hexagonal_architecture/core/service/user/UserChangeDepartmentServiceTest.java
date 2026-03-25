package com.littlesekii.hexagonal_architecture.core.service.user;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.littlesekii.hexagonal_architecture.core.domain.Department;
import com.littlesekii.hexagonal_architecture.core.domain.User;
import com.littlesekii.hexagonal_architecture.core.exception.InvalidArgumentException;
import com.littlesekii.hexagonal_architecture.core.exception.notFound.DepartmentNotFoundException;
import com.littlesekii.hexagonal_architecture.core.exception.notFound.UserNotFoundException;
import com.littlesekii.hexagonal_architecture.core.ports.out.DepartmentRepositoryPort;
import com.littlesekii.hexagonal_architecture.core.ports.out.UserRepositoryPort;

@ExtendWith(MockitoExtension.class)
public class UserChangeDepartmentServiceTest {
    
    @Mock
    private UserRepositoryPort repositoryPort;
    @Mock
    private DepartmentRepositoryPort departmentRepositoryPort;

    private UserChangeDepartmentService service;

    @BeforeEach
    void setUp() {
        service = new UserChangeDepartmentService(repositoryPort, departmentRepositoryPort);
    }

    @Test
    void changeDepartment_withValidData_changesDepartmentAndReturnsUser() {
        User existingUser = new User(1L, "littlesekii", "Davi", null);
        Department department = new Department(1L, "Engineering");
        User updatedUser = new User(1L, "littlesekii", "Davi", department);

        Mockito.when(repositoryPort.findById(1L)).thenReturn(Optional.of(existingUser));
        Mockito.when(departmentRepositoryPort.findById(1L)).thenReturn(Optional.of(department));
        Mockito.when(repositoryPort.save(existingUser)).thenReturn(updatedUser);

        User result = service.execute(1L, 1L);

        Assertions.assertTrue(result.getDepartment().isPresent());
        Assertions.assertEquals(1L, result.getDepartment().get().getId());
        Assertions.assertEquals("Engineering", result.getDepartment().get().getName());
        Mockito.verify(repositoryPort).save(existingUser);
    }

    @Test
    void changeDepartment_withNullDepartmentId_throwsException() {
        User existingUser = new User(1L, "littlesekii", "Davi", null);

        Mockito.when(repositoryPort.findById(1L)).thenReturn(Optional.of(existingUser));

        Throwable exception = Assertions.assertThrowsExactly(
            InvalidArgumentException.class, 
            () -> service.execute(1L, null)
        );

        Assertions.assertEquals("departmentId cannot be null", exception.getMessage());
    }

    @Test
    void changeDepartment_withNonExistingUser_throwsException() {
        Mockito.when(repositoryPort.findById(999L)).thenReturn(Optional.empty());

        Throwable exception = Assertions.assertThrowsExactly(
            UserNotFoundException.class, 
            () -> service.execute(999L, 1L)
        );

        Assertions.assertEquals("user not found", exception.getMessage());
    }

    @Test
    void changeDepartment_withNonExistingDepartment_throwsException() {
        User existingUser = new User(1L, "littlesekii", "Davi", null);

        Mockito.when(repositoryPort.findById(1L)).thenReturn(Optional.of(existingUser));
        Mockito.when(departmentRepositoryPort.findById(999L)).thenReturn(Optional.empty());

        Throwable exception = Assertions.assertThrowsExactly(
            DepartmentNotFoundException.class, 
            () -> service.execute(1L, 999L)
        );

        Assertions.assertEquals("department not found", exception.getMessage());
    }
}
