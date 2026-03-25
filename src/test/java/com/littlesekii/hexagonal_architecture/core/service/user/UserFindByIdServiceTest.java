package com.littlesekii.hexagonal_architecture.core.service.user;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.littlesekii.hexagonal_architecture.core.domain.User;
import com.littlesekii.hexagonal_architecture.core.exception.notFound.UserNotFoundException;
import com.littlesekii.hexagonal_architecture.core.ports.out.UserRepositoryPort;

@ExtendWith(MockitoExtension.class)
public class UserFindByIdServiceTest {
    
    @Mock
    private UserRepositoryPort repositoryPort;

    private UserFindByIdService service;

    @BeforeEach
    void setUp() {
        service = new UserFindByIdService(repositoryPort);
    }

    @Test
    void findAll_withExistingUser_returnsUser() {
        User user = new User(1L, "littlesekii", "Davi", null);

        Mockito.when(repositoryPort.findById(1L)).thenReturn(Optional.of(user));

        User result = service.execute(1L);

        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("littlesekii", result.getUsername());
        Mockito.verify(repositoryPort).findById(1L);
    }

    @Test
    void findAll_withNonExistentUser_throwsException() {
        Mockito.when(repositoryPort.findById(999L)).thenReturn(Optional.empty());

        Throwable exception = Assertions.assertThrows(
            UserNotFoundException.class, 
            () -> service.execute(999L)
        );
        Assertions.assertEquals("user not found", exception.getMessage());
    }
}
