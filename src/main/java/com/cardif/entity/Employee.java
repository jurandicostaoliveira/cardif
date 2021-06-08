package com.cardif.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuppressWarnings("serial")
@Builder
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

    @ManyToMany(
            targetEntity = Position.class,
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "history_employee_position",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "position_id")
    )
    private Set<Position> historyPosition = new HashSet<>();

}
