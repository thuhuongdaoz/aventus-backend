package com.example.aventusbackend.repository;


import com.example.aventusbackend.entity.Candidate;
import com.example.aventusbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    boolean existsByEmail(String email);

    Optional<Candidate> findByEmail(String email);
}
