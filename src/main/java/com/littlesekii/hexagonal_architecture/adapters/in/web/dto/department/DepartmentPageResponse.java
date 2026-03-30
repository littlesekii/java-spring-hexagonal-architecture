package com.littlesekii.hexagonal_architecture.adapters.in.web.dto.department;

import java.util.List;

import com.littlesekii.hexagonal_architecture.core.domain.Department;
import com.littlesekii.hexagonal_architecture.core.wrapper.PageWrapper;

public record DepartmentPageResponse (
    List<DepartmentResponse> content,
    int pageNumber,
    int pageSize,
    long totalElements,
    int totalPages
) {

    public static DepartmentPageResponse fromDomainPageWrapper(PageWrapper<Department> domainPageWrapper) {
        return new DepartmentPageResponse(
            domainPageWrapper.content().stream()
                .map(DepartmentResponse::fromDomain)
                .toList(),
            domainPageWrapper.page(),
            domainPageWrapper.size(),
            domainPageWrapper.totalElements(),
            domainPageWrapper.totalPages()
        );
    }
}
