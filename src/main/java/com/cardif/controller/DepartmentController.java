package com.cardif.controller;

import com.cardif.entity.Department;
import com.cardif.service.DepartmentService;
import com.cardif.util.Validation;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "REST API -  Department")
@CrossOrigin(origins = "*")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping("/department/find/{id}")
    public ResponseEntity<Department> findById(@PathVariable int id) {
        Department department = service.findById(id);

        if (Validation.isZero(department.getDepartmentId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(department);
    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/department")
    public ResponseEntity<Department> save(@RequestBody Department body) {
        return new ResponseEntity<>(service.save(body), HttpStatus.CREATED);
    }

    @PutMapping("/department")
    public ResponseEntity<Void> replace(@RequestBody Department body) {
        service.replace(body);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
