package com.littlesekii.hexagonal_architecture.core.service.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.littlesekii.hexagonal_architecture.core.domain.User;
import com.littlesekii.hexagonal_architecture.core.exception.IntegrityViolationException;
import com.littlesekii.hexagonal_architecture.core.exception.InvalidArgumentException;
import com.littlesekii.hexagonal_architecture.core.ports.out.UserRepositoryPort;

@ExtendWith(MockitoExtension.class)
public class UserCreateServiceTest {
    
    @Mock
    private UserRepositoryPort repositoryPort;

    private UserCreateService service;

    @BeforeEach
    void setUp() {
        service = new UserCreateService(repositoryPort);
    }

    @Test
    void create_withValidUser_savesAndReturnsUser() {
        User inputUser = new User(null, "littlesekii", "Davi", null);
        User savedUser = new User(1L, "littlesekii", "Davi", null);

        Mockito.when(repositoryPort.existsByUsername("littlesekii"))
            .thenReturn(false);
        Mockito.when(repositoryPort.save(inputUser))
            .thenReturn(savedUser);

        User result = service.execute(inputUser);

        Assertions.assertEquals(result.getId(), 1L);
        Assertions.assertEquals(result.getUsername(), "littlesekii");
        Mockito.verify(repositoryPort).save(inputUser);
    }

    @Test
    void create_withDuplicateUsername_throwsException() {
        User user = new User(null, "littlesekii", "Davi", null);

        Mockito.when(repositoryPort.existsByUsername("littlesekii")).thenReturn(true);

        Throwable exception = Assertions.assertThrowsExactly(
            IntegrityViolationException.class, 
            () -> service.execute(user)
        );

        Assertions.assertEquals("this username is already taken", exception.getMessage());
    }

    @Test
    void create_withNullUsername_throwsException() {
        User user = new User(null, null, "Davi", null);

        Throwable exception = Assertions.assertThrowsExactly(
            InvalidArgumentException.class, 
            () -> service.execute(user)
        );

        Assertions.assertEquals("username cannot be blank", exception.getMessage());
    }

    @Test
    void create_withNullName_throwsException() {
        User user = new User(null, "littlesekii", null, null);

        Throwable exception = Assertions.assertThrowsExactly(
            InvalidArgumentException.class, 
            () -> service.execute(user)
        );

        Assertions.assertEquals("name cannot be blank", exception.getMessage());
    }

    @Test
    void create_withEmptyUsername_throwsException() {
        User user = new User(null, "", "Davi", null);

        Throwable exception = Assertions.assertThrowsExactly(
            InvalidArgumentException.class, 
            () -> service.execute(user)
        );

        Assertions.assertEquals("username cannot be blank", exception.getMessage());
    }

    @Test
    void create_withEmptyName_throwsException() {
        User user = new User(null, "littlesekii", "", null);

        Throwable exception = Assertions.assertThrowsExactly(
            InvalidArgumentException.class, 
            () -> service.execute(user)
        );

        Assertions.assertEquals("name cannot be blank", exception.getMessage());
    }
}
