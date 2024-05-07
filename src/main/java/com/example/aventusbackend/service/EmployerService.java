package com.example.aventusbackend.service;

import com.example.aventusbackend.dto.request.CandidateRequest;
import com.example.aventusbackend.dto.request.EmployerRequest;
import com.example.aventusbackend.dto.response.CandidateResponse;
import com.example.aventusbackend.dto.response.EmployerResponse;
import com.example.aventusbackend.entity.Candidate;
import com.example.aventusbackend.entity.Employer;
import com.example.aventusbackend.enums.Role;
import com.example.aventusbackend.exception.AppException;
import com.example.aventusbackend.exception.ErrorCode;
import com.example.aventusbackend.mapper.CandidateMapper;
import com.example.aventusbackend.mapper.EmployerMapper;
import com.example.aventusbackend.repository.CandidateRepository;
import com.example.aventusbackend.repository.EmployerRepository;
import com.example.aventusbackend.repository.RoleRepository;
import com.example.aventusbackend.repository.WardRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class EmployerService {
    EmployerRepository employerRepository;
    RoleRepository roleRepository;
    WardRepository wardRepository;
    EmployerMapper employerMapper;
    PasswordEncoder passwordEncoder;


    public EmployerResponse create(EmployerRequest request){
        if (employerRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.USER_EXISTED);
//        Employer employer = new Employer();
//        employer.setEmail(request.getEmail());
////        employer.setPassword(request.getPassword());
//        employer.setName(request.getName());
//        employer.setCompanyName(request.getCompanyName());
//        employer.setAddress(request.getAddress());

        Employer employer = employerMapper.toEmployer(request);


        employer.setPassword(passwordEncoder.encode(request.getPassword()));

        var role = roleRepository.findById(Role.EMPLOYER.name()).orElseThrow(
                () -> new RuntimeException());
        employer.setRole(role);

        var ward = wardRepository.findById(request.getWard()).orElseThrow(
                () -> new RuntimeException());
        employer.setWard(ward);

        return employerMapper.toEmployerResponse(employerRepository.save(employer));
    }
}
