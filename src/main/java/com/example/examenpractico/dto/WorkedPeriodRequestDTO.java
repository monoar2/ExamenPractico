package com.example.examenpractico.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class WorkedPeriodRequestDTO {

    private Integer employeeId;
    private Date startDate;
    private Date endDate;}
