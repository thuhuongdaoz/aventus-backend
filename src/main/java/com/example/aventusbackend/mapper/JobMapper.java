package com.example.aventusbackend.mapper;

import com.example.aventusbackend.dto.request.CandidateCreateRequest;
import com.example.aventusbackend.dto.request.CandidateUpdateRequest;
import com.example.aventusbackend.dto.request.JobRequest;
import com.example.aventusbackend.dto.response.CandidateResponse;
import com.example.aventusbackend.entity.Candidate;
import com.example.aventusbackend.entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface JobMapper {
    Job toJob(JobRequest request);
//     toCandidateResponse(Candidate candidate);
//
//
    void updateJob(@MappingTarget Job job, JobRequest request);
}
