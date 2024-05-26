package com.example.aventusbackend.controller;

import com.example.aventusbackend.dto.request.EmployerCreateRequest;
import com.example.aventusbackend.dto.request.EmployerUpdateRequest;
import com.example.aventusbackend.dto.response.CandidateResponse;
import com.example.aventusbackend.dto.response.EmployerResponse;
import com.example.aventusbackend.service.EmployerService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class EmployerController {
    EmployerService employerService;
    @PostMapping
    EmployerResponse create(@RequestBody @Valid EmployerCreateRequest request){
        return  employerService.create(request);
    }
    @GetMapping("/myInfo")
    EmployerResponse getMyInfo(){
        return employerService.getMyInfo();
    }

    @PutMapping("/myInfo")
    EmployerResponse updateMyInfo(@RequestBody @Valid EmployerUpdateRequest request){
        return employerService.updateMyInfo(request);

    }
}
