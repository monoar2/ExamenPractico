package com.example.examenpractico.dto;

import com.example.examenpractico.entity.Genders;
import com.example.examenpractico.entity.Jobs;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
public class EmployeesDTO {

    private String name;
    private String lastName;
    private Date birthdate;
    private int genderId;
    private int jobId;
    private Genders gender;
    private Jobs job;


    public EmployeesDTO() {
    }

    public EmployeesDTO(String name, String lastName, Date birthdate, int genderId, int jobId) {
        this.name = name;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.genderId = genderId;
        this.jobId = jobId;
    }

    public EmployeesDTO(String name, String lastName, Date birthdate, Genders gender, Jobs job) {
        this.name = name;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.job = job;
    }
}