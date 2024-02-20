package com.example.examenpractico.repository;

import com.example.examenpractico.entity.EmployeeWorkedHours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface EmployeeWorkedHoursRepository extends JpaRepository<EmployeeWorkedHours, Integer> {

    boolean existsByEmployeeIdAndWorkedDate(int employeeId, Date workedDate);

    Optional<List<EmployeeWorkedHours>> findByEmployeeIdAndWorkedDateBetween(Integer employeeId, Date startDate, Date endDate);
}
