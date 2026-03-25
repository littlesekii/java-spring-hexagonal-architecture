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
public class UserDeleteServiceTest {

    @Mock
    private UserRepositoryPort repositoryPort;

    private UserDeleteService service;

    @BeforeEach
    void setUp() {
        service = new UserDeleteService(repositoryPort);
    }
    
    @Test
    void delete_withExistingUser_deletesUser() {
        User user = new User(1L, "littlesekii", "Davi", null);

        Mockito.when(repositoryPort.findById(1L)).thenReturn(Optional.of(user));

        service.execute(1L);

        Mockito.verify(repositoryPort).deleteById(1L);
    }

     @Test
    void delete_withNonExistentUser_throwsException() {
        Mockito.when(repositoryPort.findById(999L)).thenReturn(Optional.empty());

        Throwable exception = Assertions.assertThrowsExactly(
            UserNotFoundException.class, 
            () -> service.execute(999L)
        );

        Assertions.assertEquals("user not found", exception.getMessage());
    }
}
