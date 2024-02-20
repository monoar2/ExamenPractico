package com.example.examenpractico.controller;

import com.example.examenpractico.dto.*;
import com.example.examenpractico.entity.EmployeeWorkedHours;
import com.example.examenpractico.service.EmployeeWorkedHoursService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController("employeeWorkedHours")
@RequestMapping("/api/employeeWorkedHours")
@Slf4j
public class EmployeeWorkedHoursController {
    @Autowired
    private  EmployeeWorkedHoursService employeeWorkedHoursService;

    @PostMapping("/addEmployeeWorkedHours")
    public ResponseEntity<ResponseDTO> addEmployeeWorkedHours(@RequestBody WorkedHoursRequestDTO workedHoursRequestDTO) {
        return employeeWorkedHoursService.addEmployeeWorkedHours(workedHoursRequestDTO);
    }

    @GetMapping("/getTotalWorkedHours")
    public ResponseEntity<TotalWorkedHoursResponseDTO> getTotalWorkedHours(WorkedPeriodRequestDTO  workedPeriodRequestDTO) {
        return employeeWorkedHoursService.getTotalWorkedHours(workedPeriodRequestDTO);
    }

    @GetMapping("/getTotalPayments")
    public ResponseEntity<TotalPayResponseDTO> getTotalPayments(WorkedPeriodRequestDTO  workedPeriodRequestDTO) {
        return employeeWorkedHoursService.getTotalPayments(workedPeriodRequestDTO);
    }
}
