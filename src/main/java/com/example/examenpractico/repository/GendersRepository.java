package com.example.examenpractico.repository;

import com.example.examenpractico.entity.Genders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GendersRepository extends JpaRepository<Genders, Integer> {
}
