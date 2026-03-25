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
import com.littlesekii.hexagonal_architecture.core.exception.IntegrityViolationException;
import com.littlesekii.hexagonal_architecture.core.exception.InvalidArgumentException;
import com.littlesekii.hexagonal_architecture.core.exception.notFound.DepartmentNotFoundException;
import com.littlesekii.hexagonal_architecture.core.ports.out.DepartmentRepositoryPort;

@ExtendWith(MockitoExtension.class)
public class DepartmentUpdateServiceTest {
    
    @Mock
    private DepartmentRepositoryPort repositoryPort;

    private DepartmentUpdateService service;

    @BeforeEach
    void setUp() {
        service = new DepartmentUpdateService(repositoryPort);
    }

    @Test
    void update_withValidData_updatesNameAndReturnDepartment() {
        Department existingDepartment = new Department(1L, "Engineering");
        Department updateData = new Department(null, "Marketing");
        Department updatedDepartment = new Department(1L, "Marketing");

        Mockito.when(repositoryPort.findById(1L)).thenReturn(Optional.of(existingDepartment));
        Mockito.when(repositoryPort.existsByName("Marketing")).thenReturn(false);
        Mockito.when(repositoryPort.save(existingDepartment)).thenReturn(updatedDepartment);

        Department result = service.execute(1L, updateData);

        Assertions.assertEquals("Marketing", result.getName());
        Mockito.verify(repositoryPort).save(existingDepartment);
    }

    @Test
    void update_withSameName_updatesAndReturnDepartment() {
        Department existingDepartment = new Department(1L, "Engineering");
        Department updateData = new Department(null, "Engineering");
        Department updatedDepartment = new Department(1L, "Engineering");

        Mockito.when(repositoryPort.findById(1L)).thenReturn(Optional.of(existingDepartment));
        Mockito.when(repositoryPort.save(existingDepartment)).thenReturn(updatedDepartment);

        Department result = service.execute(1L, updateData);

        Assertions.assertEquals("Engineering", result.getName());
        Mockito.verify(repositoryPort).save(existingDepartment);

        Mockito.verify(repositoryPort, Mockito.never())
            .existsByName(Mockito.any());
    }

    @Test
    void update_withNewUniqueName_updatesNameAndReturnDepartment() {
        Department existingDepartment = new Department(1L, "Engineering");
        Department updateData = new Department(null, "Marketing");
        Department updatedDepartment = new Department(1L, "Marketing");

        Mockito.when(repositoryPort.findById(1L)).thenReturn(Optional.of(existingDepartment));
        Mockito.when(repositoryPort.existsByName("Marketing")).thenReturn(false);
        Mockito.when(repositoryPort.save(existingDepartment)).thenReturn(updatedDepartment);

        Department result = service.execute(1L, updateData);

        Assertions.assertEquals("Marketing", result.getName());
        Mockito.verify(repositoryPort).save(existingDepartment);
    }

    @Test
    void update_withDuplicateName_throwsException() {
        Department existingDepartment = new Department(1L, "Engineering");
        Department updateData = new Department(null, "Marketing");

        Mockito.when(repositoryPort.findById(1L)).thenReturn(Optional.of(existingDepartment));
        Mockito.when(repositoryPort.existsByName("Marketing")).thenReturn(true);

        Throwable exception = Assertions.assertThrowsExactly(
            IntegrityViolationException.class, 
            () -> service.execute(1L, updateData)
        );

        Assertions.assertEquals("a department with this name already exists", exception.getMessage());
    }

    @Test
    void update_withNonExistentDepartment_throwsException() {
        Department updateData = new Department(null, "Marketing");

        Mockito.when(repositoryPort.findById(999L)).thenReturn(Optional.empty());

        Throwable exception = Assertions.assertThrowsExactly(
            DepartmentNotFoundException.class, 
            () -> service.execute(999L, updateData)
        );

        Assertions.assertEquals("department not found", exception.getMessage());    
    }

    @Test
    void update_withNullName_throwsException() {
        Department updateData = new Department(null, null);

        Throwable exception = Assertions.assertThrowsExactly(
            InvalidArgumentException.class, 
            () -> service.execute(1L, updateData)
        );

        Assertions.assertEquals("name cannot be blank", exception.getMessage());    
    }

    @Test
    void update_withEmptyName_throwsException() {
        Department updateData = new Department(null, "");

        Throwable exception = Assertions.assertThrowsExactly(
            InvalidArgumentException.class, 
            () -> service.execute(1L, updateData)
        );

        Assertions.assertEquals("name cannot be blank", exception.getMessage());    
    }
}
