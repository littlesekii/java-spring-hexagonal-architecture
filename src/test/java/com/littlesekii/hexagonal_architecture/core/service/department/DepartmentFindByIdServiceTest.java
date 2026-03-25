package com.littlesekii.hexagonal_architecture.core.service.department;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.littlesekii.hexagonal_architecture.core.domain.Department;
import com.littlesekii.hexagonal_architecture.core.exception.notFound.DepartmentNotFoundException;
import com.littlesekii.hexagonal_architecture.core.ports.out.DepartmentRepositoryPort;

@ExtendWith(MockitoExtension.class)
public class DepartmentFindByIdServiceTest {
    
    @Mock
    private DepartmentRepositoryPort repositoryPort;

    private DepartmentFindByIdService service;

    @BeforeEach
    void setUp() {
        service = new DepartmentFindByIdService(repositoryPort);
    }

    @Test
    void findById_withExistingDepartment_returnsDepartment() {
        Department department = new Department(1L, "Engineering");

        Mockito.when(repositoryPort.findById(1L)).thenReturn(Optional.of(department));

        Department result = service.execute(1L);

        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("Engineering", result.getName());
        Mockito.verify(repositoryPort).findById(1L);
    }

    @Test
    void findById_withNonExistentDepartment_throwsException() {
        Mockito.when(repositoryPort.findById(999L)).thenReturn(Optional.empty());

        Throwable exception = Assertions.assertThrows(
            DepartmentNotFoundException.class, 
            () -> service.execute(999L)
        );
        Assertions.assertEquals("department not found", exception.getMessage());
    }
}
