package com.example.aventusbackend.mapper;

import com.example.aventusbackend.dto.request.CandidateCreateRequest;
import com.example.aventusbackend.dto.request.CandidateUpdateRequest;
import com.example.aventusbackend.dto.response.CandidateResponse;
import com.example.aventusbackend.entity.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CandidateMapper {
//    @Mapping(target = "email", source = "request.email")
//    @Mapping(target = "password", source = "request.password")
//    @Mapping(target = "name", source = "request.name")
    Candidate toCandidate(CandidateCreateRequest request);
    CandidateResponse toCandidateResponse(Candidate candidate);


    void updateCandidate(@MappingTarget Candidate candidate, CandidateUpdateRequest request);
}
