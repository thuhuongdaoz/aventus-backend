package com.example.aventusbackend.repository;


import com.example.aventusbackend.entity.Apply;
import com.example.aventusbackend.entity.Job;
import com.example.aventusbackend.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface ApplyRepository extends JpaRepository<Apply, Integer> , JpaSpecificationExecutor<Apply> {
    boolean existsByCandidateIdAndJobId(Integer candidateId, Integer jobId);
}
