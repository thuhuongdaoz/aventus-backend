package com.example.aventusbackend.controller;

import com.example.aventusbackend.dto.request.CandidateCreateRequest;
import com.example.aventusbackend.dto.request.CandidateUpdateRequest;
import com.example.aventusbackend.dto.response.CandidateResponse;
import com.example.aventusbackend.dto.response.UserResponse;
import com.example.aventusbackend.service.CandidateService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidates")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CandidateController {
    CandidateService candidateService;
    @PostMapping
    CandidateResponse create(@RequestBody @Valid CandidateCreateRequest request){
                return candidateService.create(request);
    }

    @GetMapping("/myInfo")
    CandidateResponse getMyInfo(){
        return candidateService.getMyInfo();
    }

    @PutMapping("/myInfo")
    CandidateResponse updateMyInfo(@RequestBody @Valid CandidateUpdateRequest request){
        return candidateService.updateMyInfo(request);

    }


}
