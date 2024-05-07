package com.example.aventusbackend.repository;


import com.example.aventusbackend.entity.District;
import com.example.aventusbackend.entity.Province;
import com.example.aventusbackend.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DistrictRepository extends JpaRepository<District, String> {
    List<District> findByProvince(Province province);

}
