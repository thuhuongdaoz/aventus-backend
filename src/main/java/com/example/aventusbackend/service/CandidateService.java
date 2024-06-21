package com.example.aventusbackend.service;

import com.example.aventusbackend.dto.request.ApplyRequest;
import com.example.aventusbackend.dto.request.CandidateCreateRequest;
import com.example.aventusbackend.dto.request.CandidateUpdateRequest;
import com.example.aventusbackend.dto.response.CandidateResponse;
import com.example.aventusbackend.entity.Apply;
import com.example.aventusbackend.entity.Candidate;
import com.example.aventusbackend.exception.AppException;
import com.example.aventusbackend.exception.ErrorCode;
import com.example.aventusbackend.mapper.ApplyMapper;
import com.example.aventusbackend.mapper.CandidateMapper;
import com.example.aventusbackend.enums.Role;
import com.example.aventusbackend.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CandidateService {
    private final JobRepository jobRepository;
    CandidateRepository candidateRepository;
    RoleRepository roleRepository;
    WardRepository wardRepository;
    MajorRepository majorRepository;
    DegreeRepository degreeRepository;
    EnglishLevelRepository englishLevelRepository;
    ApplyRepository applyRepository;

    CandidateMapper candidateMapper;
    ApplyMapper applyMapper;
    PasswordEncoder passwordEncoder;


    public CandidateResponse create(CandidateCreateRequest request) {
        if (candidateRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.EMAIL_EXISTED);

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

    public CandidateResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();

        Candidate candidate = candidateRepository.findByEmail(email).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return candidateMapper.toCandidateResponse(candidate);
    }

    public CandidateResponse updateMyInfo( CandidateUpdateRequest request) {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();

        Candidate candidate = candidateRepository.findByEmail(email).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        candidateMapper.updateCandidate(candidate, request);
        if (request.getWard_code() != null){
            var ward = wardRepository.findById(request.getWard_code()).orElseThrow(
                    () -> new RuntimeException());
            candidate.setWard(ward);
        }
        if (request.getMajor_id() != 0){
            var major = majorRepository.findById(request.getMajor_id()).orElseThrow(
                    () -> new RuntimeException());
            candidate.setMajor(major);
        }
        if (request.getDegree_id() != 0){
            var degree = degreeRepository.findById(request.getDegree_id()).orElseThrow(
                    () -> new RuntimeException());
            candidate.setDegree(degree);
        }
        if (request.getEnglish_level_id() != 0){
            var englishLevel = englishLevelRepository.findById(request.getEnglish_level_id()).orElseThrow(
                    () -> new RuntimeException());
            candidate.setEnglishLevel(englishLevel);
        }

        return candidateMapper.toCandidateResponse(candidateRepository.save(candidate));
    }
    public Apply apply( ApplyRequest request) {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();

        Candidate candidate = candidateRepository.findByEmail(email).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Apply apply = applyMapper.toApply(request);
        apply.setCandidate(candidate);
        var job = jobRepository.findById(request.getJobId()).orElseThrow(
                () -> new RuntimeException());
        apply.setJob(job);
        var major = majorRepository.findById(request.getMajorId()).orElseThrow(
                () -> new RuntimeException());
        apply.setMajor(major);
        var degree = degreeRepository.findById(request.getDegreeId()).orElseThrow(
                () -> new RuntimeException());
        apply.setDegree(degree);
        var englishLevel = englishLevelRepository.findById(request.getEnglishLevelId()).orElseThrow(
                () -> new RuntimeException());
        apply.setEnglishLevel(englishLevel);

        apply.setStatus(1);


        return applyRepository.save(apply);
    }
}
