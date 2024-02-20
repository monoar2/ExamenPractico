package com.example.examenpractico.dto;

import com.example.examenpractico.entity.Employees;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class EmployeesListResponseDTO {
    private List<Employees> employees;
    private boolean success;
}
