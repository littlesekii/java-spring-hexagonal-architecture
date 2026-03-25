package com.littlesekii.hexagonal_architecture.core.service.department;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.littlesekii.hexagonal_architecture.core.domain.Department;
import com.littlesekii.hexagonal_architecture.core.exception.IntegrityViolationException;
import com.littlesekii.hexagonal_architecture.core.exception.InvalidArgumentException;
import com.littlesekii.hexagonal_architecture.core.ports.out.DepartmentRepositoryPort;

@ExtendWith(MockitoExtension.class)
public class DepartmentCreateServiceTest {
    
    @Mock
    private DepartmentRepositoryPort repositoryPort;

    private DepartmentCreateService service;

    @BeforeEach
    void setUp() {
        service = new DepartmentCreateService(repositoryPort);
    }

    @Test
    void create_withValidDepartment_savesAndReturnsDepartment() {
        Department inputDepartment = new Department(null, "Engineering");
        Department savedDepartment = new Department(1L, "Engineering");

        Mockito.when(repositoryPort.existsByName("Engineering"))
            .thenReturn(false);
        Mockito.when(repositoryPort.save(inputDepartment))
            .thenReturn(savedDepartment);

        Department result = service.execute(inputDepartment);

        Assertions.assertEquals(result.getId(), 1L);
        Assertions.assertEquals(result.getName(), "Engineering");
        Mockito.verify(repositoryPort).save(inputDepartment);
    }

    @Test
    void create_withDuplicateName_throwsException() {
        Department department = new Department(null, "Engineering");

        Mockito.when(repositoryPort.existsByName("Engineering")).thenReturn(true);

        Throwable exception = Assertions.assertThrowsExactly(
            IntegrityViolationException.class, 
            () -> service.execute(department)
        );

        Assertions.assertEquals("a department with this name already exists", exception.getMessage());
    }

    @Test
    void create_withNullName_throwsException() {
        Department department = new Department(null, null);

        Throwable exception = Assertions.assertThrowsExactly(
            InvalidArgumentException.class, 
            () -> service.execute(department)
        );

        Assertions.assertEquals("name cannot be blank", exception.getMessage());
    }

    @Test
    void create_withEmptyName_throwsException() {
        Department department = new Department(null, "");

        Throwable exception = Assertions.assertThrowsExactly(
            InvalidArgumentException.class, 
            () -> service.execute(department)
        );

        Assertions.assertEquals("name cannot be blank", exception.getMessage());
    }
}
