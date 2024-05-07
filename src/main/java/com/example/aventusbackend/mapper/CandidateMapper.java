package com.example.aventusbackend.mapper;

import com.example.aventusbackend.dto.request.CandidateRequest;
import com.example.aventusbackend.dto.response.CandidateResponse;
import com.example.aventusbackend.entity.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CandidateMapper {
    @Mapping(target = "email", source = "request.email")
    @Mapping(target = "password", source = "request.password")
    @Mapping(target = "name", source = "request.name")
    Candidate toCandidate(CandidateRequest request);
    CandidateResponse toCandidateResponse(Candidate candidate);
//
//    @Mapping(target = "roles", ignore = true)
//    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
