package com.example.aventusbackend.repository;


import com.example.aventusbackend.entity.District;
import com.example.aventusbackend.entity.Province;
import com.example.aventusbackend.entity.Role;
import com.example.aventusbackend.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WardRepository extends JpaRepository<Ward, String> {
    List<Ward> findByDistrict(District district);
}
