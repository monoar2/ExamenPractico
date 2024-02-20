package com.example.examenpractico.service;

import com.example.examenpractico.dto.EmployeesDTO;
import com.example.examenpractico.dto.EmployeesListResponseDTO;
import com.example.examenpractico.dto.ResponseDTO;
import com.example.examenpractico.entity.Employees;
import com.example.examenpractico.entity.Genders;
import com.example.examenpractico.entity.Jobs;
import com.example.examenpractico.repository.EmployeesRepository;
import com.example.examenpractico.repository.GendersRepository;
import com.example.examenpractico.repository.JobsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeesService {
    private final EmployeesRepository employeesRepository;
    private final GendersRepository gendersRepository;
    private final JobsRepository jobsRepository;

    public EmployeesService(EmployeesRepository employeesRepository, GendersRepository gendersRepository, JobsRepository jobsRepository) {
        this.employeesRepository = employeesRepository;
        this.gendersRepository = gendersRepository;
        this.jobsRepository = jobsRepository;
    }

    public ResponseEntity<ResponseDTO> addEmployees(EmployeesDTO employeeDTO) {
        if (!employeesRepository.existsByNameAndLastName(employeeDTO.getName(), employeeDTO.getLastName())) {

            if (isAdult(employeeDTO.getBirthdate())) {

                Optional<Genders> gender = gendersRepository.findById(employeeDTO.getGenderId());
                Optional<Jobs> job = jobsRepository.findById(employeeDTO.getJobId());

                if (gender.isPresent() && job.isPresent()) {
                    Employees employee = employeesRepository.save(new Employees(employeeDTO.getName(), employeeDTO.getLastName(), employeeDTO.getBirthdate(), gender.get(), job.get()));

                    ResponseDTO responseDTO = new ResponseDTO(employee.getId(), true);
                    return ResponseEntity.ok(responseDTO);

                } else {
                    //gender or job not found
                    ResponseDTO responseDTO = new ResponseDTO(null, false);
                    return ResponseEntity.ok(responseDTO);
                }
            } else {
                // Not an adult
                ResponseDTO responseDTO = new ResponseDTO(null, false);
                return ResponseEntity.ok(responseDTO);

            }
        } else {
            // Name or last name already exist
            ResponseDTO responseDTO = new ResponseDTO(null, false);
            return ResponseEntity.ok(responseDTO);
        }
    }

    public ResponseEntity<EmployeesListResponseDTO> getEmployeesByJobId(int jobId) {
        Optional<Jobs> job = jobsRepository.findById(jobId);

        if (job.isPresent()) {
            List<Employees> employees = employeesRepository.findAllByJobId(jobId);
            EmployeesListResponseDTO employeesListResponseDTO = new EmployeesListResponseDTO(employees, true);
            return ResponseEntity.ok(employeesListResponseDTO);
        } else {
            //job not found
            return ResponseEntity.ok(new EmployeesListResponseDTO(null, false));
        }
    }

    private boolean isAdult(Date birthdate) {
        LocalDate localBirthdate = new java.sql.Date(birthdate.getTime()).toLocalDate();

        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(localBirthdate, currentDate);

        return period.getYears() >= 18;
    }
}
