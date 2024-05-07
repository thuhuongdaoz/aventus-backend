package com.example.aventusbackend.controller;

import com.example.aventusbackend.dto.request.CandidateRequest;
import com.example.aventusbackend.dto.response.CandidateResponse;
import com.example.aventusbackend.service.CandidateService;
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
@RequestMapping("/candidates")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CandidateController {
    CandidateService candidateService;
    @PostMapping
    CandidateResponse create(@RequestBody @Valid CandidateRequest request){
                return candidateService.create(request);
    }


}
