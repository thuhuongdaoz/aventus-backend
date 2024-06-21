package com.example.aventusbackend.controller;

import com.example.aventusbackend.dto.request.CandidateCreateRequest;
import com.example.aventusbackend.dto.request.JobRequest;
import com.example.aventusbackend.dto.request.TopsisSearchJobRequest;
import com.example.aventusbackend.dto.response.CandidateResponse;
import com.example.aventusbackend.dto.response.JobResponse;
import com.example.aventusbackend.entity.Degree;
import com.example.aventusbackend.entity.Job;
import com.example.aventusbackend.service.DegreeService;
import com.example.aventusbackend.service.JobService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class JobController {
    JobService jobService;

    @GetMapping
    List<Job> search(@RequestParam(required = false) Integer employerId,
                     @RequestParam(required = false) String name,
                     @RequestParam(required = false) Integer careerId,
                     @RequestParam(required = false) Integer degreeId,
                     @RequestParam(required = false) Integer experience,
                     @RequestParam(required = false) Integer offer){

        return jobService.search(employerId, name, careerId, degreeId, experience, offer);
    }
    @PostMapping("/topsisSearch")
    List<JobResponse> topsisSearch(@RequestBody @Valid TopsisSearchJobRequest request){
        return jobService.topsisSearch(request);
    }
    @GetMapping("/{jobId}")
    Job getById(@PathVariable("jobId") Integer jobId){
        return jobService.getById(jobId);

    }
    @PostMapping
    Job create(@RequestBody @Valid JobRequest request){
        return jobService.create(request);
    }

    @PutMapping("/{jobId}")
    Job update(@PathVariable Integer jobId, @RequestBody @Valid JobRequest request){
        return
                jobService.update(jobId, request);
    }

    @DeleteMapping("/{jobId}")
    String delete(@PathVariable Integer jobId){
        jobService.delete(jobId);
        return "Xoá việc làm thành công";

    }




}
