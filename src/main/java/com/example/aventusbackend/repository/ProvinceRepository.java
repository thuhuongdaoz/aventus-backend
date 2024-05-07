package com.example.aventusbackend.repository;


import com.example.aventusbackend.entity.District;
import com.example.aventusbackend.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProvinceRepository extends JpaRepository<Province, String> {


}
