package com.example.aventusbackend.service;

import com.example.aventusbackend.entity.Career;
import com.example.aventusbackend.entity.Degree;
import com.example.aventusbackend.repository.CareerRepository;
import com.example.aventusbackend.repository.DegreeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CareerService {
    CareerRepository careerRepository;
    public List<Career> getAll(){
        return careerRepository.findAll();
    }
}
