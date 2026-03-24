package com.littlesekii.hexagonal_architecture.adapters.in.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.littlesekii.hexagonal_architecture.adapters.in.web.dto.user.UserCreateRequest;
import com.littlesekii.hexagonal_architecture.adapters.in.web.dto.user.UserResponse;
import com.littlesekii.hexagonal_architecture.adapters.in.web.dto.user.UserUpdateRequest;
import com.littlesekii.hexagonal_architecture.core.ports.in.user.UserCreateUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.in.user.UserDeleteUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.in.user.UserFindAllUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.in.user.UserFindByIdUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.in.user.UserPartialUpdateUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.in.user.UserUpdateUseCase;



@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserFindAllUseCase userFindAllUseCase;
    private final UserFindByIdUseCase userFindByIdUseCase;
    private final UserCreateUseCase userCreateUseCase;
    private final UserUpdateUseCase userUpdateUseCase; 
    private final UserPartialUpdateUseCase userPartialUpdateUseCase;
    private final UserDeleteUseCase userDeleteUseCase;

    public UserController(
        UserFindAllUseCase userFindAllUseCase,
        UserFindByIdUseCase userFindByIdUseCase,
        UserCreateUseCase userCreateUseCase,
        UserUpdateUseCase userUpdateUseCase,
        UserPartialUpdateUseCase userPartialUpdateUseCase,
        UserDeleteUseCase userDeleteUseCase
    ) {
        this.userFindAllUseCase = userFindAllUseCase;
        this.userFindByIdUseCase = userFindByIdUseCase;
        this.userCreateUseCase = userCreateUseCase;
        this.userUpdateUseCase = userUpdateUseCase;
        this.userPartialUpdateUseCase = userPartialUpdateUseCase;
        this.userDeleteUseCase = userDeleteUseCase;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserResponse> res = userFindAllUseCase.execute().stream()
            .map(UserResponse::fromDomain)
            .toList();
            
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        UserResponse res = UserResponse.fromDomain(userFindByIdUseCase.execute(id));
        return ResponseEntity.ok().body(res);
    }
    
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserCreateRequest req) {
        userCreateUseCase.execute(req.toDomain());
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UserUpdateRequest req) {
        userUpdateUseCase.execute(id, req.toDomain());
        return ResponseEntity.status(200).build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Void> partialUpdate(@PathVariable Long id, @RequestBody UserUpdateRequest req) {
        userPartialUpdateUseCase.execute(id, req.toDomain());
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userDeleteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
    
}
