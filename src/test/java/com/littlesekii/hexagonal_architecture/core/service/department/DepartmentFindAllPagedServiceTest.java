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
import com.littlesekii.hexagonal_architecture.core.wrapper.PageWrapper;

@ExtendWith(MockitoExtension.class)
public class DepartmentFindAllPagedServiceTest {

    @Mock
    private DepartmentRepositoryPort repositoryPort;

    private DepartmentFindAllPagedService service;

    @BeforeEach
    void setUp() {
        service = new DepartmentFindAllPagedService(repositoryPort);
    }
    
    @Test
    void findAllPaged_withExistingDepartments_returnsAllDepartmentsPaged() {
        Integer page = 0;
        Integer size = 10;

        PageWrapper<Department> departmentPage = new PageWrapper<>(
            List.of(
                new Department(1L, "Engineering"),
                new Department(2L, "Marketing")
            ), 
            page, 
            size, 
            2, 
            2
        );

        Mockito.when(repositoryPort.findAllPaged(page, size)).thenReturn(departmentPage);

        PageWrapper<Department> result = service.execute(page, size);

        Assertions.assertEquals(2, result.totalElements());
        Assertions.assertEquals(1L, result.content().getFirst().getId());
        Assertions.assertEquals("Engineering", result.content().getFirst().getName());

        Mockito.verify(repositoryPort).findAllPaged(page, size);
    }

    @Test
    void findAllPaged_withNullParameters_returnsDepartmentsPagedWithDefaultPageParameters() {
        Integer defaultPage = 0;
        Integer defaultSize = 10;

        PageWrapper<Department> departmentPage = new PageWrapper<>(
            List.of(
                new Department(1L, "Engineering"),
                new Department(2L, "Marketing")
            ), 
            defaultPage, 
            defaultSize, 
            2, 
            2
        );

        Mockito.when(repositoryPort.findAllPaged(defaultPage, defaultSize))
            .thenReturn(departmentPage);

        PageWrapper<Department> result = service.execute(null, null);

        Assertions.assertEquals(defaultPage, result.page());
        Assertions.assertEquals(defaultSize, result.size());
        Assertions.assertEquals(2, result.totalElements());
        Assertions.assertEquals(1L, result.content().getFirst().getId());
        Assertions.assertEquals("Engineering", result.content().getFirst().getName());

        Mockito.verify(repositoryPort).findAllPaged(defaultPage, defaultSize);
    }

    @Test
    void findAllPaged_withNonExistentDepartments_returnsEmpty() {
        Integer defaultPage = 0;
        Integer defaultSize = 10;

        PageWrapper<Department> departmentPage = new PageWrapper<>(
            List.of(), 
            defaultPage,
            defaultSize,
            0, 
            0
        );

        Mockito.when(repositoryPort.findAllPaged(defaultPage, defaultSize))
            .thenReturn(departmentPage);

        PageWrapper<Department> result = service.execute(null, null);

        Assertions.assertEquals(defaultPage, result.page());
        Assertions.assertEquals(defaultSize, result.size());
        Assertions.assertTrue(result.content().isEmpty());

        Mockito.verify(repositoryPort).findAllPaged(defaultPage, defaultSize);
    }
}
