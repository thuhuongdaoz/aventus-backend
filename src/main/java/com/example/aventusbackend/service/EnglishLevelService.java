package com.example.aventusbackend.service;

import com.example.aventusbackend.entity.EnglishLevel;
import com.example.aventusbackend.entity.Major;
import com.example.aventusbackend.repository.EnglishLevelRepository;
import com.example.aventusbackend.repository.MajorRepository;
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
public class EnglishLevelService {
    EnglishLevelRepository englishLevelRepository;
    public List<EnglishLevel> getAll(){
        return englishLevelRepository.findAll();
    }

}
