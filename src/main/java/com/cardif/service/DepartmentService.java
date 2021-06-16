package com.cardif.service;

import com.cardif.entity.Department;
import com.cardif.repository.DepartmentRepository;
import com.cardif.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    public Department findById(int id) {
        return repository.findById(id).orElse(new Department());
    }

    public List<Department> findAll() {
        return repository.findAll();
    }

    public Department save(Department department) {
        return repository.save(department);
    }

    public void replace(Department department) {
        if (!Validation.isZero(findById(department.getDepartmentId()).getDepartmentId())) {
            repository.save(department);
        }
    }

    public void delete(int id) {
        repository.delete(findById(id));
    }

}
