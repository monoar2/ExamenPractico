package com.example.examenpractico.repository;

import com.example.examenpractico.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface EmployeesRepository extends JpaRepository<Employees, Integer> {
    List<Employees> findAllByJobId(int jobId);

    boolean existsByNameAndLastName(String name, String lastName);

    Employees findByNameAndLastNameAndBirthdateAndGenderIdAndJobId(String name, String lastName, Date birthdate, int genderId, int jobId);
}
