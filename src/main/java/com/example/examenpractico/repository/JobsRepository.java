package com.example.examenpractico.repository;

import com.example.examenpractico.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobsRepository extends JpaRepository<Jobs, Integer> {
}
