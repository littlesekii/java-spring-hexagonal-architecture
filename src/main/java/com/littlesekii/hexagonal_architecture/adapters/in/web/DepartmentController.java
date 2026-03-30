package com.littlesekii.hexagonal_architecture.adapters.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.littlesekii.hexagonal_architecture.adapters.in.web.dto.PageRequest;
import com.littlesekii.hexagonal_architecture.adapters.in.web.dto.department.DepartmentCreateRequest;
import com.littlesekii.hexagonal_architecture.adapters.in.web.dto.department.DepartmentPageResponse;
import com.littlesekii.hexagonal_architecture.adapters.in.web.dto.department.DepartmentResponse;
import com.littlesekii.hexagonal_architecture.adapters.in.web.dto.department.DepartmentUpdateRequest;
import com.littlesekii.hexagonal_architecture.core.ports.in.department.DepartmentCreateUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.in.department.DepartmentDeleteUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.in.department.DepartmentFindAllPagedUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.in.department.DepartmentFindAllUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.in.department.DepartmentFindByIdUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.in.department.DepartmentPartialUpdateUseCase;
import com.littlesekii.hexagonal_architecture.core.ports.in.department.DepartmentUpdateUseCase;



@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private DepartmentFindAllUseCase departmentFindAllUseCase;
    private DepartmentFindAllPagedUseCase departmentFindAllPagedUseCase;
    private DepartmentFindByIdUseCase departmentFindByIdUseCase;
    private DepartmentCreateUseCase departmentCreateUseCase;
    private DepartmentUpdateUseCase departmentUpdateUseCase;
    private DepartmentPartialUpdateUseCase departmentPartialUpdateUseCase;
    private DepartmentDeleteUseCase departmentDeleteUseCase;
    
    public DepartmentController(
        DepartmentFindAllUseCase departmentFindAllUseCase,
        DepartmentFindAllPagedUseCase departmentFindAllPagedUseCase,
        DepartmentFindByIdUseCase departmentFindByIdUseCase,
        DepartmentCreateUseCase departmentCreateUseCase,
        DepartmentUpdateUseCase departmentUpdateUseCase,
        DepartmentPartialUpdateUseCase departmentPartialUpdateUseCase,
        DepartmentDeleteUseCase departmentDeleteUseCase
    ) {
        this.departmentFindAllUseCase = departmentFindAllUseCase;
        this.departmentFindAllPagedUseCase = departmentFindAllPagedUseCase;
        this.departmentFindByIdUseCase = departmentFindByIdUseCase;
        this.departmentCreateUseCase = departmentCreateUseCase;
        this.departmentUpdateUseCase = departmentUpdateUseCase;
        this.departmentPartialUpdateUseCase = departmentPartialUpdateUseCase;
        this.departmentDeleteUseCase = departmentDeleteUseCase;
    }

    @GetMapping
    public ResponseEntity<DepartmentPageResponse> findAll(@ModelAttribute PageRequest req) {    
        DepartmentPageResponse res = DepartmentPageResponse.fromDomainPageWrapper(
            departmentFindAllPagedUseCase.execute(req.page(), req.size())
        );
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> findById(@PathVariable Long id) {
        DepartmentResponse res = DepartmentResponse.fromDomain(departmentFindByIdUseCase.execute(id));
        return ResponseEntity.ok().body(res);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody DepartmentCreateRequest req) {
        departmentCreateUseCase.execute(req.toDomain());
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody DepartmentUpdateRequest req) {
        departmentUpdateUseCase.execute(id, req.toDomain());
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> partialUpdate(@PathVariable Long id, @RequestBody DepartmentUpdateRequest req) {
        departmentPartialUpdateUseCase.execute(id, req.toDomain());
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departmentDeleteUseCase.execute(id);
        return ResponseEntity.noContent().build(); 
    }
}
