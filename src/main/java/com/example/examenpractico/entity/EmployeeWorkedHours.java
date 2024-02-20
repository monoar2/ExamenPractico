package com.example.examenpractico.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeWorkedHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int workedHours;
    private Date workedDate;

    @ManyToOne
    @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "EMPLOYEE_ID"))
    private Employees employee;

}