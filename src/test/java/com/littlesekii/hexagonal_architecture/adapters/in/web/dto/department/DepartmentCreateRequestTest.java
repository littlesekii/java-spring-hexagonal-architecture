package com.littlesekii.hexagonal_architecture.adapters.in.web.dto.department;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.littlesekii.hexagonal_architecture.core.domain.Department;

class DepartmentCreateRequestTest {

    @Test
    void toDomain_withValidData() {
        DepartmentCreateRequest request = new DepartmentCreateRequest("Engineering");

        Department department = request.toDomain();

        Assertions.assertNull(department.getId());
        Assertions.assertEquals("Engineering", department.getName());
    }
}
