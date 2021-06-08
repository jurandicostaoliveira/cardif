package com.cardif.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuppressWarnings("serial")
@Builder
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

}
