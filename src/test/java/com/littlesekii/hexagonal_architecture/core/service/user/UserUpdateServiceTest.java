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
public class UserUpdateServiceTest {
    
    @Mock
    private UserRepositoryPort repositoryPort;

    private UserUpdateService service;

    @BeforeEach
    void setUp() {
        service = new UserUpdateService(repositoryPort);
    }

    @Test
    void update_withValidData_updatesEveryFieldAndReturnUser() {
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
    void update_withSameUsername_updatesNameAndReturnUser() {
        User existingUser = new User(1L, "littlesekii", "Davi", null);
        User updateData = new User(null, "littlesekii", "Davi Bacalhau", null);
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
    void update_withNewUniqueUsername_updatesUsernameAndReturnUser() {
        User existingUser = new User(1L, "littlesekii", "Davi", null);
        User updateData = new User(null, "bacalhzu", "Davi", null);
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
    void update_withDuplicateUsername_throwsException() {
        User existingUser = new User(1L, "littlesekii", "Davi", null);
        User updateData = new User(null, "bacalhzu", "Davi Bacalhau", null);

        Mockito.when(repositoryPort.findById(1L)).thenReturn(Optional.of(existingUser));
        Mockito.when(repositoryPort.existsByUsername("bacalhzu")).thenReturn(true);

        Throwable exception = Assertions.assertThrowsExactly(
            IntegrityViolationException.class, 
            () -> service.execute(1L, updateData)
        );

        Assertions.assertEquals("this username is already taken", exception.getMessage());
    }

    @Test
    void update_withNonExistentUser_throwsException() {
        User updateData = new User(null, "bacalhzu", "Davi Bacalhau", null);

        Mockito.when(repositoryPort.findById(999L)).thenReturn(Optional.empty());

        Throwable exception = Assertions.assertThrowsExactly(
            UserNotFoundException.class, 
            () -> service.execute(999L, updateData)
        );

        Assertions.assertEquals("user not found", exception.getMessage());    
    }

    @Test
    void update_withNullUsername_throwsException() {
        User updateData = new User(null, null, "Davi Bacalhau", null);

        Throwable exception = Assertions.assertThrowsExactly(
            InvalidArgumentException.class, 
            () -> service.execute(1L, updateData)
        );

        Assertions.assertEquals("username cannot be blank", exception.getMessage());    
    }
    
    @Test
    void update_withNullName_throwsException() {
        User updateData = new User(null, "littlesekii", null, null);

        Throwable exception = Assertions.assertThrowsExactly(
            InvalidArgumentException.class, 
            () -> service.execute(1L, updateData)
        );

        Assertions.assertEquals("name cannot be blank", exception.getMessage());    
    }

    @Test
    void update_withEmptyUsername_throwsException() {
        User updateData = new User(null, "", "Davi Bacalhau", null);

        Throwable exception = Assertions.assertThrowsExactly(
            InvalidArgumentException.class, 
            () -> service.execute(1L, updateData)
        );

        Assertions.assertEquals("username cannot be blank", exception.getMessage());    
    }
    
    @Test
    void update_withEmptyName_throwsException() {
        User updateData = new User(null, "littlesekii", "", null);

        Throwable exception = Assertions.assertThrowsExactly(
            InvalidArgumentException.class, 
            () -> service.execute(1L, updateData)
        );

        Assertions.assertEquals("name cannot be blank", exception.getMessage());    
    }


}
