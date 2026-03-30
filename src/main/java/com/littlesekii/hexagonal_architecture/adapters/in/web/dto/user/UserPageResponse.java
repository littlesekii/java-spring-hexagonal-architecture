package com.littlesekii.hexagonal_architecture.adapters.in.web.dto.user;

import java.util.List;

import com.littlesekii.hexagonal_architecture.core.domain.User;
import com.littlesekii.hexagonal_architecture.core.wrapper.PageWrapper;

public record UserPageResponse (
    List<UserResponse> content,
    int pageNumber,
    int pageSize,
    long totalElements,
    int totalPages
) {

    public static UserPageResponse fromDomainPageWrapper(PageWrapper<User> domainPageWrapper) {
        return new UserPageResponse(
            domainPageWrapper.content().stream()
                .map(UserResponse::fromDomain)
                .toList(),
            domainPageWrapper.page(),
            domainPageWrapper.size(),
            domainPageWrapper.totalElements(),
            domainPageWrapper.totalPages()
        );
    }
}
