package com.example.examenpractico.controller;

import com.example.examenpractico.dto.EmployeesDTO;
import com.example.examenpractico.dto.EmployeesListResponseDTO;
import com.example.examenpractico.dto.ResponseDTO;
import com.example.examenpractico.entity.Employees;
import com.example.examenpractico.service.EmployeesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("employees")
@RequestMapping("/api/employees")
@Slf4j
public class EmployeesController {
    @Autowired
    private EmployeesService employeesService;

    @PostMapping("/addEmployees")
    public ResponseEntity<ResponseDTO> addEmployees(@RequestBody EmployeesDTO employeeDTO) {
        return employeesService.addEmployees(employeeDTO);
    }

    @GetMapping("/getEmployeesByJobId/{jobId}")
    public ResponseEntity getEmployeesByJobId(@PathVariable int jobId) {
        return employeesService.getEmployeesByJobId(jobId);
    }
}
