package com.example.aventusbackend.repository;


import com.example.aventusbackend.entity.District;
import com.example.aventusbackend.entity.EnglishLevel;
import com.example.aventusbackend.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EnglishLevelRepository extends JpaRepository<EnglishLevel, Integer> {

}
