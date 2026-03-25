package com.littlesekii.hexagonal_architecture.core.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.littlesekii.hexagonal_architecture.core.exception.InvalidArgumentException;

public class UserTest {
    
    @Test
    void validate_withNullUsername_throwsException() {
        User user = new User(1L, null, "Davi", null);

        Throwable exception = Assertions.assertThrowsExactly(
            InvalidArgumentException.class, 
            user::validate
        );

        Assertions.assertEquals("username cannot be blank", exception.getMessage());
    }

    @Test
    void validate_withNullName_throwsException() {
        User user = new User(1L, "littlesekii", null, null);

        Throwable exception = Assertions.assertThrowsExactly(
            InvalidArgumentException.class, 
            user::validate
        );
        
        Assertions.assertEquals("name cannot be blank", exception.getMessage());
    }

    @Test
    void validate_withEmptyUsername_throwsException() {
        User user = new User(1L, "", "Davi", null);

        Throwable exception = Assertions.assertThrowsExactly(
            InvalidArgumentException.class, 
            user::validate
        );

        Assertions.assertEquals("username cannot be blank", exception.getMessage());
    }

    @Test
    void validate_withEmptyName_throwsException() {
        User user = new User(1L, "littlesekii", "", null);

        Throwable exception = Assertions.assertThrowsExactly(
            InvalidArgumentException.class, 
            user::validate
        );
        
        Assertions.assertEquals("name cannot be blank", exception.getMessage());
    }

    @Test
    void validate_withValidData_noException() {
        User user = new User(1L, "littlesekii", "Davi", null); 
        
        Assertions.assertDoesNotThrow(user::validate);
    }

    @Test
    void validate_withNullDepartment_noException() {
        User user = new User(1L, "littlesekii", "Davi", null); 

        Assertions.assertDoesNotThrow(user::validate);
    }

    @Test
    void updateUsername_withNewValue_updatesUsername() {
        User user = new User(1L, "littlesekii", "Davi", null); 

        user.updateUsername("sekii");

        Assertions.assertEquals("sekii", user.getUsername());
    }

    @Test
    void updateName_withNewValue_updatesName() {
        User user = new User(1L, "littlesekii", "Davi", null); 

        user.updateName("Davi Bacalhau");
        
        Assertions.assertEquals("Davi Bacalhau", user.getName());
    }

    @Test
    void updateDepartment_withNewValue_updatesDepartment() {
        User user = new User(1L, "littlesekii", "Davi", null); 
        Department department = new Department(1L, "Engineering");

        user.updateDepartment(department);

        Assertions.assertTrue(user.getDepartment().isPresent());
        Assertions.assertEquals(department, user.getDepartment().get());
    }
}
