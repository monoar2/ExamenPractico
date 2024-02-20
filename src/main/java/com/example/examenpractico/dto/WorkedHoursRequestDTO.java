package com.example.examenpractico.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class WorkedHoursRequestDTO {
    private Integer employeeId;
    private Integer workedHours;
    private Date workedDate;

    public WorkedHoursRequestDTO() {

    }
}
