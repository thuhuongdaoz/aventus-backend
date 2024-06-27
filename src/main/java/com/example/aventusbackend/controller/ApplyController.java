package com.example.aventusbackend.controller;

import com.example.aventusbackend.dto.request.ApplyRequest;
import com.example.aventusbackend.dto.request.ChangeStatusRequest;
import com.example.aventusbackend.dto.request.TopsisSearchApplyRequest;
import com.example.aventusbackend.dto.request.TopsisSearchJobRequest;
import com.example.aventusbackend.dto.response.ApplyResponse;
import com.example.aventusbackend.dto.response.JobResponse;
import com.example.aventusbackend.dto.response.TopsisSearchApplyResponse;
import com.example.aventusbackend.dto.response.WardResponse;
import com.example.aventusbackend.entity.Apply;
import com.example.aventusbackend.entity.Job;
import com.example.aventusbackend.service.ApplyService;
import com.example.aventusbackend.service.WardService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applies")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplyController {
    ApplyService applyService;

    @GetMapping("/check")
    public boolean checkApplication(@RequestParam Integer candidateId, @RequestParam Integer jobId) {
        return applyService.hasCandidateAppliedForJob(candidateId, jobId);

    }

    @GetMapping
    List<Apply> search(@RequestParam(required = false) Integer employerId,
                       @RequestParam(required = false) Integer jobId,
                       @RequestParam(required = false) String name,
                       @RequestParam(required = false) Integer majorId,
                       @RequestParam(required = false) Integer degreeId,
                       @RequestParam(required = false) Integer experience,
                       @RequestParam(required = false) Integer englishLevelId,
                       @RequestParam(required = false) Integer applicationStatus){

        return applyService.search(employerId, jobId, name, majorId, degreeId, experience, englishLevelId, applicationStatus);

    }
    @PostMapping("/topsisSearch")
    TopsisSearchApplyResponse topsisSearch(@RequestBody @Valid TopsisSearchApplyRequest request){
        return applyService.topsisSearch(request);
    }
    @GetMapping("/{applyId}")
    Apply getById(@PathVariable("applyId") Integer applyId){
        return applyService.getById(applyId);

    }
    @PostMapping("/{applyId}/changeStatus")
    boolean changeStatus(@PathVariable("applyId") Integer applyId, @RequestBody @Valid ChangeStatusRequest request){
        return applyService.changeStatus(applyId, request);
    }
    @PostMapping("/apply")
    Apply apply(@RequestBody @Valid ApplyRequest request){
        return applyService.apply(request);

    }
}
