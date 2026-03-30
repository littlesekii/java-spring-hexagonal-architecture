package com.littlesekii.hexagonal_architecture.core.service.user;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.littlesekii.hexagonal_architecture.core.domain.User;
import com.littlesekii.hexagonal_architecture.core.ports.out.UserRepositoryPort;
import com.littlesekii.hexagonal_architecture.core.wrapper.PageWrapper;

@ExtendWith(MockitoExtension.class)
public class UserFindAllPagedServiceTest {

    @Mock
    private UserRepositoryPort repositoryPort;

    private UserFindAllPagedService service;

    @BeforeEach
    void setUp() {
        service = new UserFindAllPagedService(repositoryPort);
    }
    
    @Test
    void findAllPaged_withExistingUsers_returnsAllUsersPaged() {
        Integer page = 0;
        Integer size = 10;

        PageWrapper<User> userPage = new PageWrapper<>(
            List.of(
                new User(1L, "littlesekii", "Davi", null),
                new User(2L, "sekii", "Davi Bacalhau", null)
            ), 
            page, 
            size, 
            2, 
            2
        );

        Mockito.when(repositoryPort.findAllPaged(page, size)).thenReturn(userPage);

        PageWrapper<User> result = service.execute(page, size);

        Assertions.assertEquals(2, result.totalElements());
        Assertions.assertEquals(1L, result.content().getFirst().getId());
        Assertions.assertEquals("littlesekii", result.content().getFirst().getUsername());

        Mockito.verify(repositoryPort).findAllPaged(page, size);

    }

    @Test
    void findAllPaged_withNullParameters_returnsUsersPagedWithDefaultPageParameters() {

        Integer defaultPage = 0;
        Integer defaultSize = 10;

        PageWrapper<User> userPage = new PageWrapper<>(
            List.of(
                new User(1L, "littlesekii", "Davi", null),
                new User(2L, "sekii", "Davi Bacalhau", null)
            ), 
            defaultPage, 
            defaultSize, 
            2, 
            2
        );

        Mockito.when(repositoryPort.findAllPaged(defaultPage, defaultSize))
            .thenReturn(userPage);

        PageWrapper<User> result = service.execute(null, null);

        Assertions.assertEquals(defaultPage, result.page());
        Assertions.assertEquals(defaultSize, result.size());
        Assertions.assertEquals(2, result.totalElements());
        Assertions.assertEquals(1L, result.content().getFirst().getId());
        Assertions.assertEquals("littlesekii", result.content().getFirst().getUsername());

        Mockito.verify(repositoryPort).findAllPaged(defaultPage, defaultSize);
    }

    @Test
    void findAllPaged_withNonExistentUsers_returnsEmpty() {
        Integer defaultPage = 0;
        Integer defaultSize = 10;

        PageWrapper<User> userPage = new PageWrapper<>(
            List.of(), 
            defaultPage,
            defaultSize,
            0, 
            0
        );

        Mockito.when(repositoryPort.findAllPaged(defaultPage, defaultSize))
            .thenReturn(userPage);

        PageWrapper<User> result = service.execute(null, null);

        Assertions.assertEquals(defaultPage, result.page());
        Assertions.assertEquals(defaultSize, result.size());
        Assertions.assertTrue(result.content().isEmpty());

        Mockito.verify(repositoryPort).findAllPaged(defaultPage, defaultSize);
    }
}
