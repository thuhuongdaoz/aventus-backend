package com.example.aventusbackend.controller;

import com.example.aventusbackend.entity.Degree;
import com.example.aventusbackend.entity.EnglishLevel;
import com.example.aventusbackend.service.DegreeService;
import com.example.aventusbackend.service.EnglishLevelService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/degrees")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class DegreeController {
    DegreeService degreeService;

    @GetMapping
    List<Degree> getAll(){
        return degreeService.getAll();

    }

}
