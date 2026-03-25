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
public class DepartmentDeleteServiceTest {

    @Mock
    private DepartmentRepositoryPort repositoryPort;

    private DepartmentDeleteService service;

    @BeforeEach
    void setUp() {
        service = new DepartmentDeleteService(repositoryPort);
    }
    
    @Test
    void delete_withExistingDepartment_deletesDepartment() {
        Department department = new Department(1L, "Engineering");

        Mockito.when(repositoryPort.findById(1L)).thenReturn(Optional.of(department));

        service.execute(1L);

        Mockito.verify(repositoryPort).deleteById(1L);
    }

     @Test
    void delete_withNonExistentDepartment_throwsException() {
        Mockito.when(repositoryPort.findById(999L)).thenReturn(Optional.empty());

        Throwable exception = Assertions.assertThrowsExactly(
            DepartmentNotFoundException.class, 
            () -> service.execute(999L)
        );

        Assertions.assertEquals("department not found", exception.getMessage());
    }
}
