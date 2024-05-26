package com.example.aventusbackend.service;

import com.example.aventusbackend.dto.request.JobRequest;
import com.example.aventusbackend.entity.Employer;
import com.example.aventusbackend.entity.Job;
import com.example.aventusbackend.exception.AppException;
import com.example.aventusbackend.exception.ErrorCode;
import com.example.aventusbackend.mapper.JobMapper;
import com.example.aventusbackend.repository.*;
import com.example.aventusbackend.specification.JobSpecification;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JobService {
    JobRepository jobRepository;
    EmployerRepository employerRepository;
    CareerRepository careerRepository;
    DegreeRepository degreeRepository;
    WardRepository wardRepository;
    JobMapper jobMapper;
    public List<Job> search(Integer employerId, String name, Integer careerId, Integer degreeId, Integer experience, Integer offer){
        Specification<Job> spec = Specification.where(JobSpecification.hasEmployerId(employerId))
                .and(JobSpecification.hasName(name))
                .and(JobSpecification.hasCareerId(careerId))
                .and(JobSpecification.hasDegreeId(degreeId))
                .and(JobSpecification.hasExperience(experience))
                .and(JobSpecification.hasMinOfferAndMaxOffer(offer));
        return jobRepository.findAll(spec);

    }

    public Job getById(Integer id){
        return jobRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.JOB_NOT_EXISTED));
    }


    public Job create(JobRequest request) {
        Job job = jobMapper.toJob(request);
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();
        Employer employer = employerRepository.findByEmail(email).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));
        job.setEmployer(employer);
        var career = careerRepository.findById(request.getCareerId()).orElseThrow(
                () -> new RuntimeException());
        job.setCareer(career);
        var degree = degreeRepository.findById(request.getDegreeId()).orElseThrow(
                () -> new RuntimeException());
        job.setDegree(degree);
        var ward = wardRepository.findById(request.getWard_code()).orElseThrow(
                () -> new RuntimeException());
        job.setWard(ward);
        return jobRepository.save(job);
    }

    public Job update(Integer jobId, JobRequest request) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new AppException(ErrorCode.JOB_NOT_EXISTED));

        jobMapper.updateJob(job, request);
        var career = careerRepository.findById(request.getCareerId()).orElseThrow(
                () -> new RuntimeException());
        job.setCareer(career);
        var degree = degreeRepository.findById(request.getDegreeId()).orElseThrow(
                () -> new RuntimeException());
        job.setDegree(degree);
        var ward = wardRepository.findById(request.getWard_code()).orElseThrow(
                () -> new RuntimeException());
        job.setWard(ward);
        return jobRepository.save(job);
    }
    public void delete(Integer jobId){
        jobRepository.deleteById(jobId);
    }

}
