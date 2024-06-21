package com.example.aventusbackend.mapper;

import com.example.aventusbackend.dto.request.ApplyRequest;
import com.example.aventusbackend.dto.request.CandidateCreateRequest;
import com.example.aventusbackend.dto.request.CandidateUpdateRequest;
import com.example.aventusbackend.dto.response.ApplyResponse;
import com.example.aventusbackend.dto.response.CandidateResponse;
import com.example.aventusbackend.entity.Apply;
import com.example.aventusbackend.entity.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ApplyMapper {
    Apply toApply(ApplyRequest request);
    ApplyResponse toApplyResponse(Apply apply);
//
//
//    void updateCandidate(@MappingTarget Candidate candidate, CandidateUpdateRequest request);
}
