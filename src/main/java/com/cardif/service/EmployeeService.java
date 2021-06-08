package com.cardif.service;

import com.cardif.entity.Employee;
import com.cardif.entity.Position;
import com.cardif.repository.EmployeeRepository;
import com.cardif.util.Calculator;
import com.cardif.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private PositionService positionService;

    @Autowired
    private DepartmentService departmentService;

    public Employee findById(int id) {
        return repository.findById(id).orElse(new Employee());
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Employee save(Employee employee) {
        Position position = positionService.findById(
                employee.getCurrentPosition().getPositionId()
        );

        if (Validation.isZero(position.getPositionId())) {
            return new Employee();
        }

        employee.setEmployeeAge(Calculator.currentAge(employee.getEmployeeBirthday()));
        employee.setCurrentPosition(position);
        return repository.save(employee);
    }

    public void replace(Employee employee) {
        Employee currentEmployee = findById(employee.getEmployeeId());

        if (Validation.largerZero(currentEmployee.getEmployeeId())) {
            Position position = positionService.findById(
                    currentEmployee.getCurrentPosition().getPositionId()
            );

            employee.setHistoryPosition(Set.of(position));
            save(employee);
        }
    }

    public void delete(int id) {
        repository.delete(findById(id));
    }

}
