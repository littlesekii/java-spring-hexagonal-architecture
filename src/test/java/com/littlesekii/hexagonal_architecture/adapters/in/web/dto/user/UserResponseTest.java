package com.littlesekii.hexagonal_architecture.adapters.in.web.dto.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.littlesekii.hexagonal_architecture.core.domain.Department;
import com.littlesekii.hexagonal_architecture.core.domain.User;

class UserResponseTest {

    @Test
    void fromDomain_withDepartment() {
        Department department = new Department(1L, "Engineering");
        User user = new User(1L, "littlesekii", "Davi", department);

        UserResponse response = UserResponse.fromDomain(user);

        Assertions.assertEquals(1L, response.id());
        Assertions.assertEquals("littlesekii", response.username());
        Assertions.assertEquals("Davi", response.name());
        Assertions.assertNotNull(response.department());
        Assertions.assertEquals(1L, response.department().id());
        Assertions.assertEquals("Engineering", response.department().name());
    }

    @Test
    void fromDomain_withoutDepartment() {
        User user = new User(1L, "littlesekii", "Davi", null);

        UserResponse response = UserResponse.fromDomain(user);

        Assertions.assertEquals(1L, response.id());
        Assertions.assertEquals("littlesekii", response.username());
        Assertions.assertEquals("Davi", response.name());
        Assertions.assertNull(response.department());
    }
}
