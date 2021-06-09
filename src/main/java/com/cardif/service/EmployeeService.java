package com.cardif.service;

import com.cardif.entity.Department;
import com.cardif.entity.Employee;
import com.cardif.entity.Position;
import com.cardif.repository.EmployeeRepository;
import com.cardif.util.Calculator;
import com.cardif.util.Validation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Log4j2
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
        Position position = positionService
                .findById(employee.getCurrentPosition().getPositionId());

        Department department = departmentService
                .findById(employee.getDepartment().getDepartmentId());

        if (Validation.containsZero(List.of(
                position.getPositionId(), department.getDepartmentId()
        ))) {
            return new Employee();
        }

        employee.setEmployeeAge(Calculator.currentAge(employee.getEmployeeBirthday()));
        employee.setCurrentPosition(position);
        employee.setDepartment(department);
        return repository.save(employee);
    }

    public void replace(Employee employee) {
        Employee currentEmployee = findById(employee.getEmployeeId());

        if (!Validation.isZero(currentEmployee.getEmployeeId())) {
            Set<Position> historyPosition = currentEmployee.getHistoryPosition();
            historyPosition.add(currentEmployee.getCurrentPosition());
            employee.setHistoryPosition(historyPosition);
            save(employee);
        }
    }

    public void delete(int id) {
        repository.delete(findById(id));
    }

}
