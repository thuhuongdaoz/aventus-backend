package com.example.aventusbackend.controller;

import com.example.aventusbackend.dto.request.CandidateRequest;
import com.example.aventusbackend.dto.request.EmployerRequest;
import com.example.aventusbackend.dto.response.CandidateResponse;
import com.example.aventusbackend.dto.response.EmployerResponse;
import com.example.aventusbackend.service.EmployerService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class EmployerController {
    EmployerService employerService;
    @PostMapping
    EmployerResponse create(@RequestBody @Valid EmployerRequest request){
        return  employerService.create(request);
    }
}
