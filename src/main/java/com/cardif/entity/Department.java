package com.cardif.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@SuppressWarnings("serial")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentId;

    @Column(length = 50)
    private String departmentName;

    @ManyToMany(
            targetEntity = Employee.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "employee_department",
            joinColumns = @JoinColumn(name = "department_id", unique = true),
            inverseJoinColumns = @JoinColumn(name = "employee_id", unique = true)
    )
    private Set<Employee> employees = new HashSet<>();

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

}
