package com.example.examenpractico.service;

import com.example.examenpractico.dto.*;
import com.example.examenpractico.entity.EmployeeWorkedHours;
import com.example.examenpractico.entity.Employees;
import com.example.examenpractico.repository.EmployeeWorkedHoursRepository;
import com.example.examenpractico.repository.EmployeesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.time.LocalTime.now;

@Service
public class EmployeeWorkedHoursService {
    private final EmployeeWorkedHoursRepository employeeWorkedHoursRepository;
    private final EmployeesRepository employeesRepository;

    public EmployeeWorkedHoursService(EmployeeWorkedHoursRepository employeeWorkedHoursRepository, EmployeesRepository employeesRepository) {
        this.employeeWorkedHoursRepository = employeeWorkedHoursRepository;
        this.employeesRepository = employeesRepository;
    }

    public ResponseEntity<ResponseDTO> addEmployeeWorkedHours(WorkedHoursRequestDTO workedHoursRequestDTO) {
        Optional<Employees> employee = employeesRepository.findById(workedHoursRequestDTO.getEmployeeId());
        if (employee.isPresent()) {
            if (workedHoursRequestDTO.getWorkedHours() <= 20) {
                if (workedHoursRequestDTO.getWorkedDate().compareTo(Date.valueOf(LocalDate.now())) <= 0) {
                    if (!employeeWorkedHoursRepository.existsByEmployeeIdAndWorkedDate(workedHoursRequestDTO.getEmployeeId(), workedHoursRequestDTO.getWorkedDate())) {
                        EmployeeWorkedHours employeeWorkedHours = new EmployeeWorkedHours();
                        employeeWorkedHours.setEmployee(employee.get());
                        employeeWorkedHours.setWorkedHours(workedHoursRequestDTO.getWorkedHours());
                        employeeWorkedHours.setWorkedDate(workedHoursRequestDTO.getWorkedDate());
                        EmployeeWorkedHours savedEmployeeWorkedHours = employeeWorkedHoursRepository.save(employeeWorkedHours);

                        ResponseDTO responseDTO = new ResponseDTO(savedEmployeeWorkedHours.getId(), true);
                        return ResponseEntity.ok(responseDTO);
                    } else {
                        // Employee has already registered hours for the given date
                        ResponseDTO responseDTO = new ResponseDTO(null, false);
                        return ResponseEntity.ok(responseDTO);
                    }
                } else {
                    // Date is after today's date
                    ResponseDTO responseDTO = new ResponseDTO(null, false);
                    return ResponseEntity.ok(responseDTO);
                }
            } else {
                // Total worked hours is 20 or more
                ResponseDTO responseDTO = new ResponseDTO(null, false);
                return ResponseEntity.ok(responseDTO);
            }
        } else {
            // Employee does not exist
            ResponseDTO responseDTO = new ResponseDTO(null, false);
            return ResponseEntity.ok(responseDTO);
        }
    }

    public ResponseEntity<TotalWorkedHoursResponseDTO> getTotalWorkedHours(WorkedPeriodRequestDTO workedPeriodRequestDTO) {
        Optional<Employees> employee = employeesRepository.findById(workedPeriodRequestDTO.getEmployeeId());
        if (employee.isPresent()) {
            Optional<List<EmployeeWorkedHours>> employeeWorkedHours = employeeWorkedHoursRepository
                    .findByEmployeeIdAndWorkedDateBetween(
                            workedPeriodRequestDTO.getEmployeeId(),
                            workedPeriodRequestDTO.getStartDate(),
                            workedPeriodRequestDTO.getEndDate()
                    );
            if (employeeWorkedHours.isPresent()) {

                int totalWorkedHours = employeeWorkedHours.get().stream()
                        .mapToInt(EmployeeWorkedHours::getWorkedHours)
                        .sum();
                TotalWorkedHoursResponseDTO totalWorkedHoursResponseDTO = new TotalWorkedHoursResponseDTO(totalWorkedHours, true);

                return ResponseEntity.ok(totalWorkedHoursResponseDTO);
            } else {
                // Employee has not worked for the given dates
                TotalWorkedHoursResponseDTO totalWorkedHoursResponseDTO = new TotalWorkedHoursResponseDTO(null, false);
                return ResponseEntity.ok(totalWorkedHoursResponseDTO);}
        } else {
            // Employee does not exist
            TotalWorkedHoursResponseDTO totalWorkedHoursResponseDTO = new TotalWorkedHoursResponseDTO(null, false);
            return ResponseEntity.ok(totalWorkedHoursResponseDTO);
        }
    }

    public ResponseEntity<TotalPayResponseDTO> getTotalPayments(WorkedPeriodRequestDTO workedPeriodRequestDTO) {
        Optional<Employees> employee = employeesRepository.findById(workedPeriodRequestDTO.getEmployeeId());
        if(workedPeriodRequestDTO.getStartDate().compareTo(workedPeriodRequestDTO.getEndDate()) < 0){
            if (employee.isPresent()) {
                Optional<List<EmployeeWorkedHours>> employeeWorkedHours = employeeWorkedHoursRepository
                        .findByEmployeeIdAndWorkedDateBetween(
                                workedPeriodRequestDTO.getEmployeeId(),
                                workedPeriodRequestDTO.getStartDate(),
                                workedPeriodRequestDTO.getEndDate()
                        );
                if (employeeWorkedHours.isPresent()) {
                    Double totalWorkedHours = (double) employeeWorkedHours.get().stream()
                            .mapToInt(EmployeeWorkedHours::getWorkedHours)
                            .sum();

                    Double totalPay = totalWorkedHours * employee.get().getJob().getSalary();

                    TotalPayResponseDTO totalPayResponseDTO = new TotalPayResponseDTO(totalPay, true);
                    return ResponseEntity.ok(totalPayResponseDTO);
                } else {
                    // Employee has not worked for the given dates
                    TotalPayResponseDTO totalPayResponseDTO = new TotalPayResponseDTO(null, false);
                    return ResponseEntity.ok(totalPayResponseDTO);
                }

            }else {
                // Employee does not exist
                TotalPayResponseDTO totalPayResponseDTO = new TotalPayResponseDTO(null, false);
                return ResponseEntity.ok(totalPayResponseDTO);
            }

        } else {
            // Start date is after end date
            TotalPayResponseDTO totalPayResponseDTO = new TotalPayResponseDTO(null, false);
            return ResponseEntity.ok(totalPayResponseDTO);
        }
    }
}
