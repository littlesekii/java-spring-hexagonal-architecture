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
import com.littlesekii.hexagonal_architecture.core.exception.IntegrityViolationException;
import com.littlesekii.hexagonal_architecture.core.exception.InvalidArgumentException;
import com.littlesekii.hexagonal_architecture.core.exception.notFound.UserNotFoundException;
import com.littlesekii.hexagonal_architecture.core.ports.out.UserRepositoryPort;

@ExtendWith(MockitoExtension.class)
public class UserPartialUpdateServiceTest {
    
    @Mock
    private UserRepositoryPort repositoryPort;

    private UserPartialUpdateService service;

    @BeforeEach
    void setUp() {
        service = new UserPartialUpdateService(repositoryPort);
    }

    @Test
    void partialUpdate_withEveryFieldValid_updatesEveryFieldAndReturnUser() {
        User existingUser = new User(1L, "littlesekii", "Davi", null);
        User updateData = new User(null, "bacalhzu", "Davi Bacalhau", null);
        User updatedUser = new User(1L, "bacalhzu", "Davi Bacalhau", null);

        Mockito.when(repositoryPort.findById(1L)).thenReturn(Optional.of(existingUser));
        Mockito.when(repositoryPort.existsByUsername("bacalhzu")).thenReturn(false);
        Mockito.when(repositoryPort.save(existingUser)).thenReturn(updatedUser);

        User result = service.execute(1L, updateData);

        Assertions.assertEquals("bacalhzu", result.getUsername());
        Assertions.assertEquals("Davi Bacalhau", result.getName());
        Mockito.verify(repositoryPort).save(existingUser);
    }

    @Test
    void partialUpdate_withOnlyUsername_updatesUsernameAndReturnUser() {
        User existingUser = new User(1L, "littlesekii", "Davi", null);
        User updateData = new User(null, "bacalhzu", null, null);
        User updatedUser = new User(1L, "bacalhzu", "Davi", null);

        Mockito.when(repositoryPort.findById(1L)).thenReturn(Optional.of(existingUser));
        Mockito.when(repositoryPort.existsByUsername("bacalhzu")).thenReturn(false);
        Mockito.when(repositoryPort.save(existingUser)).thenReturn(updatedUser);

        User result = service.execute(1L, updateData);

        Assertions.assertEquals("bacalhzu", result.getUsername());
        Assertions.assertEquals("Davi", result.getName());
        Mockito.verify(repositoryPort).save(existingUser);
    }

    @Test
    void partialUpdate_withOnlyName_updatesNameAndReturnUser() {
        User existingUser = new User(1L, "littlesekii", "Davi", null);
        User updateData = new User(null, null, "Davi Bacalhau", null);
        User updatedUser = new User(1L, "littlesekii", "Davi Bacalhau", null);

        Mockito.when(repositoryPort.findById(1L)).thenReturn(Optional.of(existingUser));
        Mockito.when(repositoryPort.save(existingUser)).thenReturn(updatedUser);

        User result = service.execute(1L, updateData);

        Assertions.assertEquals("littlesekii", result.getUsername());
        Assertions.assertEquals("Davi Bacalhau", result.getName());
        Mockito.verify(repositoryPort).save(existingUser);

        Mockito.verify(repositoryPort, Mockito.never())
            .existsByUsername(Mockito.any());
    }

    @Test
    void partialUpdate_withSameUsername_updatesAndReturnUser() {
        User existingUser = new User(1L, "littlesekii", "Davi", null);
        User updateData = new User(null, "littlesekii", null, null);
        User updatedUser = new User(1L, "littlesekii", "Davi", null);

        Mockito.when(repositoryPort.findById(1L)).thenReturn(Optional.of(existingUser));
        Mockito.when(repositoryPort.save(existingUser)).thenReturn(updatedUser);

        User result = service.execute(1L, updateData);

        Assertions.assertEquals("littlesekii", result.getUsername());
        Assertions.assertEquals("Davi", result.getName());

        Mockito.verify(repositoryPort).save(existingUser);

        Mockito.verify(repositoryPort, Mockito.never())
            .existsByUsername(Mockito.any());
    }

    @Test
    void partialUpdate_withDuplicateUsername_throwsException() {
        User existingUser = new User(1L, "littlesekii", "Davi", null);
        User updateData = new User(null, "bacalhzu", null, null);

        Mockito.when(repositoryPort.findById(1L)).thenReturn(Optional.of(existingUser));
        Mockito.when(repositoryPort.existsByUsername("bacalhzu")).thenReturn(true);

        Throwable exception = Assertions.assertThrowsExactly(
            IntegrityViolationException.class, 
            () -> service.execute(1L, updateData)
        );

        Assertions.assertEquals("this username is already taken", exception.getMessage());
    }

    @Test
    void partialUpdate_withNonExistentUser_throwsException() {
        User updateData = new User(null, "bacalhzu", null, null);

        Mockito.when(repositoryPort.findById(999L)).thenReturn(Optional.empty());

        Throwable exception = Assertions.assertThrowsExactly(
            UserNotFoundException.class, 
            () -> service.execute(999L, updateData)
        );

        Assertions.assertEquals("user not found", exception.getMessage());    
    }

    @Test
    void partialUpdate_withEveryFieldNull_worksAndReturnUser() {
        User existingUser = new User(1L, "littlesekii", "Davi", null);
        User updateData = new User(null, null, null, null);
        User updatedUser = new User(1L, "littlesekii", "Davi", null);

        Mockito.when(repositoryPort.findById(1L)).thenReturn(Optional.of(existingUser));
        Mockito.when(repositoryPort.save(existingUser)).thenReturn(updatedUser);

        User result = service.execute(1L, updateData);

        Assertions.assertEquals("littlesekii", result.getUsername());
        Assertions.assertEquals("Davi", result.getName());

        Mockito.verify(repositoryPort).save(existingUser);

        Mockito.verify(repositoryPort, Mockito.never())
            .existsByUsername(Mockito.any());
    }

    @Test
    void partialUpdate_withEmptyUsername_throwsException() {
        User existingUser = new User(1L, "littlesekii", "Davi", null);
        User updateData = new User(null, "", "Davi Bacalhau", null);

        Mockito.when(repositoryPort.findById(1L)).thenReturn(Optional.of(existingUser));

        Throwable exception = Assertions.assertThrowsExactly(
            InvalidArgumentException.class, 
            () -> service.execute(1L, updateData)
        );

        Assertions.assertEquals("username cannot be blank", exception.getMessage());    
    }
    
    @Test
    void partialUpdate_withEmptyName_throwsException() {
        User existingUser = new User(1L, "littlesekii", "Davi", null);
        User updateData = new User(null, "littlesekii", "", null);

        Mockito.when(repositoryPort.findById(1L)).thenReturn(Optional.of(existingUser));

        Throwable exception = Assertions.assertThrowsExactly(
            InvalidArgumentException.class, 
            () -> service.execute(1L, updateData)
        );

        Assertions.assertEquals("name cannot be blank", exception.getMessage());    
    }


}
