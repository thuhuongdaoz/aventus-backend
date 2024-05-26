package com.example.aventusbackend.controller;

import com.example.aventusbackend.entity.Career;
import com.example.aventusbackend.entity.Major;
import com.example.aventusbackend.service.CareerService;
import com.example.aventusbackend.service.MajorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/careers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CareerController {
    CareerService careerService;

    @GetMapping
    List<Career> getAll(){
        return careerService.getAll();

    }

}
