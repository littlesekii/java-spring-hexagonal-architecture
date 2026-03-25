package com.littlesekii.hexagonal_architecture.core.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.littlesekii.hexagonal_architecture.core.exception.InvalidArgumentException;

public class DepartmentTest {
    
    @Test
    void validate_withNullName_throwsException() {
        Department department = new Department(1L, null);

        Throwable exception = Assertions.assertThrowsExactly(
            InvalidArgumentException.class, 
            department::validate
        );

        Assertions.assertEquals("name cannot be blank", exception.getMessage());
    }

    @Test
    void validate_withEmptyName_throwsException() {
        Department department = new Department(1L, "");

        Throwable exception = Assertions.assertThrowsExactly(
            InvalidArgumentException.class, 
            department::validate
        );

        Assertions.assertEquals("name cannot be blank", exception.getMessage());
    }

    @Test
    void validate_withValidData_noException() {
        Department department = new Department(1L, "Engineering"); 
        
        Assertions.assertDoesNotThrow(department::validate);
    }

    @Test
    void updateName_withNewValue_updatesName() {
        Department department = new Department(1L, "Engineering"); 

        department.updateName("Marketing");

        Assertions.assertEquals("Marketing", department.getName());
    }
}
