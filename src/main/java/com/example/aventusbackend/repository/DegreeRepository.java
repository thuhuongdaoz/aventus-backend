package com.example.aventusbackend.repository;


import com.example.aventusbackend.entity.Degree;
import com.example.aventusbackend.entity.EnglishLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DegreeRepository extends JpaRepository<Degree, Integer> {

}
