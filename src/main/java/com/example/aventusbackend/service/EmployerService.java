package com.example.aventusbackend.service;

import com.example.aventusbackend.dto.request.CandidateUpdateRequest;
import com.example.aventusbackend.dto.request.EmployerCreateRequest;
import com.example.aventusbackend.dto.request.EmployerUpdateRequest;
import com.example.aventusbackend.dto.response.CandidateResponse;
import com.example.aventusbackend.dto.response.EmployerResponse;
import com.example.aventusbackend.entity.Candidate;
import com.example.aventusbackend.entity.Employer;
import com.example.aventusbackend.enums.Role;
import com.example.aventusbackend.exception.AppException;
import com.example.aventusbackend.exception.ErrorCode;
import com.example.aventusbackend.mapper.EmployerMapper;
import com.example.aventusbackend.repository.EmployerRepository;
import com.example.aventusbackend.repository.RoleRepository;
import com.example.aventusbackend.repository.WardRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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


    public EmployerResponse create(EmployerCreateRequest request){
        if (employerRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.EMAIL_EXISTED);
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

        var ward = wardRepository.findById(request.getWard_code()).orElseThrow(
                () -> new RuntimeException());
        employer.setWard(ward);

        return employerMapper.toEmployerResponse(employerRepository.save(employer));
    }
    public EmployerResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();

        Employer employer = employerRepository.findByEmail(email).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return employerMapper.toEmployerResponse(employer);
    }
    public EmployerResponse updateMyInfo(EmployerUpdateRequest request) {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();

        Employer employer = employerRepository.findByEmail(email).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        employerMapper.updateEmployer(employer, request);
        if (request.getWard_code() != null){
            var ward = wardRepository.findById(request.getWard_code()).orElseThrow(
                    () -> new RuntimeException());
            employer.setWard(ward);
        }


        return employerMapper.toEmployerResponse(employerRepository.save(employer));
    }
}
