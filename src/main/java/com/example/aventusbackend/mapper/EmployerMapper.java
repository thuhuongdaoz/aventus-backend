package com.example.aventusbackend.mapper;

import com.example.aventusbackend.dto.request.CandidateRequest;
import com.example.aventusbackend.dto.request.EmployerRequest;
import com.example.aventusbackend.dto.response.CandidateResponse;
import com.example.aventusbackend.dto.response.EmployerResponse;
import com.example.aventusbackend.entity.Candidate;
import com.example.aventusbackend.entity.Employer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployerMapper {
    @Mapping(target = "email", source = "request.email")
    @Mapping(target = "password", source = "request.password")
    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "companyName", source = "request.companyName")
    @Mapping(target = "address", source = "request.address")
    @Mapping(target = "ward", ignore = true)
    Employer toEmployer(EmployerRequest request);
    EmployerResponse toEmployerResponse(Employer employer);
//
//    @Mapping(target = "roles", ignore = true)
//    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
