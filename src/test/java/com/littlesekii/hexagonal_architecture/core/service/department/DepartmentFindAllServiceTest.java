package com.littlesekii.hexagonal_architecture.core.service.department;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.littlesekii.hexagonal_architecture.core.domain.Department;
import com.littlesekii.hexagonal_architecture.core.ports.out.DepartmentRepositoryPort;

@ExtendWith(MockitoExtension.class)
public class DepartmentFindAllServiceTest {
    
    @Mock
    private DepartmentRepositoryPort repositoryPort;

    private DepartmentFindAllService service;

    @BeforeEach
    void setUp() {
        service = new DepartmentFindAllService(repositoryPort);
    }

    @Test
    void findAll_withExistingDepartments_returnsAllDepartments() {
        List<Department> departments = List.of(
            new Department(1L, "Engineering"),
            new Department(2L, "Marketing")
        );

        Mockito.when(repositoryPort.findAll()).thenReturn(departments);

        List<Department> result = service.execute();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Engineering", result.getFirst().getName());
        Assertions.assertEquals("Marketing", result.getLast().getName());
        Mockito.verify(repositoryPort).findAll();
    }

    @Test
    void findAll_withNonExistentDepartments_returnsEmpty() {
        List<Department> departments = List.of();

        Mockito.when(repositoryPort.findAll()).thenReturn(departments);

        List<Department> result = service.execute();

        Assertions.assertTrue(result.isEmpty());
        Mockito.verify(repositoryPort).findAll();
    }
}
