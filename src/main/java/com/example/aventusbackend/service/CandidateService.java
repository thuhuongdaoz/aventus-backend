package com.example.aventusbackend.service;

import com.example.aventusbackend.dto.request.CandidateRequest;
import com.example.aventusbackend.dto.response.CandidateResponse;
import com.example.aventusbackend.entity.Candidate;
import com.example.aventusbackend.exception.AppException;
import com.example.aventusbackend.exception.ErrorCode;
import com.example.aventusbackend.mapper.CandidateMapper;
import com.example.aventusbackend.enums.Role;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.aventusbackend.repository.CandidateRepository;
import com.example.aventusbackend.repository.RoleRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class CandidateService {
    final CandidateRepository candidateRepository;
    final RoleRepository roleRepository;
    final CandidateMapper candidateMapper;
    final PasswordEncoder passwordEncoder;


    public CandidateResponse create(CandidateRequest request){
        if (candidateRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.USER_EXISTED);

//        Candidate candidate = new Candidate();
//        candidate.setEmail(request.getEmail());
//        candidate.setPassword(request.getPassword());
//        candidate.setName(request.getName());
        Candidate candidate = candidateMapper.toCandidate(request);


        candidate.setPassword(passwordEncoder.encode(request.getPassword()));

        var role = roleRepository.findById(Role.CANDIDATE.name()).orElseThrow(
                () -> new RuntimeException());
        candidate.setRole(role);

        return candidateMapper.toCandidateResponse(candidateRepository.save(candidate));
    }
}
