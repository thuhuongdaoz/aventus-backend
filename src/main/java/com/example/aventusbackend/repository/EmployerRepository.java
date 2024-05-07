package com.example.aventusbackend.repository;


import com.example.aventusbackend.entity.Candidate;
import com.example.aventusbackend.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Integer> {
    boolean existsByEmail(String email);
}
