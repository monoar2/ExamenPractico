package com.example.examenpractico.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TotalWorkedHoursResponseDTO {

    private Integer totalWorkedHours;
    private boolean success;
}
