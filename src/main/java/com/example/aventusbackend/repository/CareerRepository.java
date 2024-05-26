package com.example.aventusbackend.repository;


import com.example.aventusbackend.entity.Career;
import com.example.aventusbackend.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CareerRepository extends JpaRepository<Career, Integer> {

}
