package com.littlesekii.hexagonal_architecture.adapters.in.web.dto.department;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.littlesekii.hexagonal_architecture.core.domain.Department;

class DepartmentResponseTest {

    @Test
    void fromDomain_withValidData() {
        Department department = new Department(1L, "Engineering");

        DepartmentResponse response = DepartmentResponse.fromDomain(department);

        Assertions.assertEquals(1L, response.id());
        Assertions.assertEquals("Engineering", response.name());
    }
}
