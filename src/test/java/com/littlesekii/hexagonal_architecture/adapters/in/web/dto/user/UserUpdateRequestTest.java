package com.littlesekii.hexagonal_architecture.adapters.in.web.dto.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.littlesekii.hexagonal_architecture.core.domain.User;

class UserUpdateRequestTest {

    @Test
    void toDomain_withValidData() {
        UserUpdateRequest request = new UserUpdateRequest("littlesekii", "Davi");

        User user = request.toDomain();

        Assertions.assertNull(user.getId());
        Assertions.assertEquals("littlesekii", user.getUsername());
        Assertions.assertEquals("Davi", user.getName());
        Assertions.assertTrue(user.getDepartment().isEmpty());
    }
}
