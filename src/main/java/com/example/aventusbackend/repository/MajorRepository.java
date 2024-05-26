package com.example.aventusbackend.repository;


import com.example.aventusbackend.entity.District;
import com.example.aventusbackend.entity.Major;
import com.example.aventusbackend.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MajorRepository extends JpaRepository<Major, Integer> {

}
