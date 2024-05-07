package com.example.aventusbackend.repository;


import com.example.aventusbackend.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    boolean existsByEmail(String email);
}
