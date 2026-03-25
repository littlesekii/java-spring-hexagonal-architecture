package com.littlesekii.hexagonal_architecture.adapters.in.web.dto.department;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.littlesekii.hexagonal_architecture.core.domain.Department;

class DepartmentUpdateRequestTest {

    @Test
    void toDomain_withValidData() {
        DepartmentUpdateRequest request = new DepartmentUpdateRequest("Marketing");

        Department department = request.toDomain();

        Assertions.assertNull(department.getId());
        Assertions.assertEquals("Marketing", department.getName());
    }
}
