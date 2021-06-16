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
import java.util.stream.Collectors;

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

    public Employee save(Employee employee) throws Exception {
        return saveOrReplace(employee, false);
    }

    public void replace(Employee employee) throws Exception {
        Employee currentEmployee = findById(employee.getEmployeeId());

        if (!Validation.isZero(currentEmployee.getEmployeeId())) {
            Set<Position> historyPosition = currentEmployee.getHistoryPosition();
            historyPosition.add(currentEmployee.getCurrentPosition());
            historyPosition = historyPosition.stream().filter(row -> {
                return row.getPositionId() != employee.getCurrentPosition().getPositionId();
            }).collect(Collectors.toSet());
            employee.setHistoryPosition(historyPosition);
            saveOrReplace(employee, true);
        }
    }

    private Employee saveOrReplace(Employee employee, boolean isUpdate) throws Exception {
        Position position = positionService.findById(employee.getCurrentPosition().getPositionId());
        Department department = departmentService.findById(employee.getDepartment().getDepartmentId());

        if (Validation.containsZero(List.of(position.getPositionId(), department.getDepartmentId()))) {
            throw new Exception("Position or department not found");
        }

        employee.setEmployeeAge(Calculator.currentAge(employee.getEmployeeBirthday()));
        employee.setCurrentPosition(position);

        if (isUpdate) {
            repository.save(employee);
            repository.updateDepartmentByEmployee(
                    department.getDepartmentId(), employee.getEmployeeId()
            );
            throw new Exception("Updated record");
        }

        Set<Employee> employees = department.getEmployees();
        employees.add(employee);
        department.setEmployees(employees);
        return departmentService.save(department).getEmployees().stream().findFirst().get();
    }

    public void delete(int id) {
        repository.delete(findById(id));
    }

}
