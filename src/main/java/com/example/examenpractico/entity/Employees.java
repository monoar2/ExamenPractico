package com.example.examenpractico.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String lastName;
    private Date birthdate;

    @ManyToOne
    @JoinColumn(name = "genderId", foreignKey = @ForeignKey(name = "GENDER_ID"))
    private Genders gender;

    @ManyToOne
    @JoinColumn(name = "jobId", foreignKey = @ForeignKey(name = "JOB_ID"))
    private Jobs job;

    public Employees(String name, String lastName, Date birthdate, Genders genders, Jobs jobs) {
        this.name = name;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.gender = genders;
        this.job = jobs;
    }
}