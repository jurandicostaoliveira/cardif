package com.cardif.controller;

import com.cardif.entity.Employee;
import com.cardif.service.EmployeeService;
import com.cardif.util.Validation;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "REST API - Employee")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/employee/find/{id}")
    public ResponseEntity<Employee> findById(@PathVariable int id) {
        Employee employee = service.findById(id);

        if (Validation.isZero(employee.getEmployeeId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(employee);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> save(@RequestBody Employee body) {
        Employee employee = service.save(body);
        if (Validation.isZero(employee.getEmployeeId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping("/employee")
    public ResponseEntity<Void> replace(@RequestBody Employee body) {
        service.replace(body);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
