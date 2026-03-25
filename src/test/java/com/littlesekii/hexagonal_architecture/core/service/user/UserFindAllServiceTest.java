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

@ExtendWith(MockitoExtension.class)
public class UserFindAllServiceTest {
    
    @Mock
    private UserRepositoryPort repositoryPort;

    private UserFindAllService service;

    @BeforeEach
    void setUp() {
        service = new UserFindAllService(repositoryPort);
    }

    @Test
    void findAll_withExistingUsers_returnsAllUsers() {
        List<User> users = List.of(
            new User(1L, "littlesekii", "Davi", null),
            new User(2L, "bacalhzu", "Davi Bacalhau", null)
        );

        Mockito.when(repositoryPort.findAll()).thenReturn(users);

        List<User> result = service.execute();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("littlesekii", result.getFirst().getUsername());
        Assertions.assertEquals("bacalhzu", result.getLast().getUsername());
        Mockito.verify(repositoryPort).findAll();
    }

    @Test
    void findAll_withNonExistentUsers_returnsEmpty() {
        List<User> users = List.of();

        Mockito.when(repositoryPort.findAll()).thenReturn(users);

        List<User> result = service.execute();

        Assertions.assertTrue(result.isEmpty());
        Mockito.verify(repositoryPort).findAll();
    }
}
