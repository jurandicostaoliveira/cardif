package com.cardif.repository;

import com.cardif.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE employee_department SET department_id = :departmentId WHERE employee_id = :employeeId",
            nativeQuery = true
    )
    void updateDepartmentByEmployee(
            @Param("departmentId") int departmentId,
            @Param("employeeId") int employeeId
    );

}
