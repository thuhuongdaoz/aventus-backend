package com.example.aventusbackend.mapper;

import com.example.aventusbackend.dto.request.CandidateUpdateRequest;
import com.example.aventusbackend.dto.request.EmployerCreateRequest;
import com.example.aventusbackend.dto.request.EmployerUpdateRequest;
import com.example.aventusbackend.dto.response.EmployerResponse;
import com.example.aventusbackend.entity.Candidate;
import com.example.aventusbackend.entity.Employer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployerMapper {
//    @Mapping(target = "email", source = "request.email")
//    @Mapping(target = "password", source = "request.password")
//    @Mapping(target = "name", source = "request.name")
//    @Mapping(target = "companyName", source = "request.companyName")
//    @Mapping(target = "address", source = "request.address")
//    @Mapping(target = "ward", ignore = true)
    Employer toEmployer(EmployerCreateRequest request);
    EmployerResponse toEmployerResponse(Employer employer);
    void updateEmployer(@MappingTarget Employer employer, EmployerUpdateRequest request);
}
