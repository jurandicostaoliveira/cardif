package com.cardif.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@SuppressWarnings("serial")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    @Column(length = 50)
    private String employeeName;

    private int employeeAge;

    @Temporal(TemporalType.DATE)
    private Date employeeBirthday;

    @Column(length = 50)
    private String employeeDocument;

    @OneToOne(targetEntity = Position.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id", referencedColumnName = "positionId")
    private Position currentPosition;

    @ManyToMany(targetEntity = Position.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "history_employee_position",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "position_id")
    )
    private Set<Position> historyPosition = new HashSet<>();

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(int employeeAge) {
        this.employeeAge = employeeAge;
    }

    public Date getEmployeeBirthday() {
        return employeeBirthday;
    }

    public void setEmployeeBirthday(Date employeeBirthday) {
        this.employeeBirthday = employeeBirthday;
    }

    public String getEmployeeDocument() {
        return employeeDocument;
    }

    public void setEmployeeDocument(String employeeDocument) {
        this.employeeDocument = employeeDocument;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Set<Position> getHistoryPosition() {
        return historyPosition;
    }

    public void setHistoryPosition(Set<Position> historyPosition) {
        this.historyPosition = historyPosition;
    }

}
