package com.example.aventusbackend.service;

import com.example.aventusbackend.dto.request.JobRequest;
import com.example.aventusbackend.dto.request.TopsisSearchJobRequest;
import com.example.aventusbackend.dto.response.JobResponse;
import com.example.aventusbackend.dto.response.TopsisSearchJobResponse;
import com.example.aventusbackend.entity.Candidate;
import com.example.aventusbackend.entity.Employer;
import com.example.aventusbackend.entity.Job;
import com.example.aventusbackend.entity.MajorCareer;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

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
    CandidateRepository candidateRepository;
    ApplyRepository applyRepository;

    JobMapper jobMapper;

    public List<Job> search(Integer employerId, String name, Integer careerId, Integer degreeId, Integer experience, Integer offer) {
        Specification<Job> spec = Specification.where(JobSpecification.hasEmployerId(employerId))
                .and(JobSpecification.hasName(name))
                .and(JobSpecification.hasCareerId(careerId))
                .and(JobSpecification.hasDegreeId(degreeId))
                .and(JobSpecification.hasExperience(experience))
                .and(JobSpecification.hasMinOfferAndMaxOffer(offer))
                .and(JobSpecification.isNotExpired());

        return jobRepository.findAll(spec);

    }

    public TopsisSearchJobResponse topsisSearch(TopsisSearchJobRequest request) {
        Specification<Job> spec = Specification.where(JobSpecification.isNotExpired());
        List<JobResponse> jobs = new ArrayList<>(jobRepository.findAll(spec).stream().map(jobMapper::toJobResponse).toList());

        //Xây dựng ma trận quyết định
        double[] sqrtSquaredSum = new double[4];
        for (JobResponse job : jobs) {
            job.setOriginPoint(new double[4]);
            job.setNormalPoint(new double[4]);
            job.setWeightPoint(new double[4]);
//            job.setPoint(new double[4]);

            // tieu chi chuyen nganh
            for (MajorCareer majorCareer : job.getCareer().getMajorCareers()) {
                if (majorCareer.getMajor().getId() == request.getMajorId()) {
                    job.getOriginPoint()[0] = majorCareer.getPoint();
                    break;
                }
            }
            sqrtSquaredSum[0] = sqrtSquaredSum[0] + job.getOriginPoint()[0] * job.getOriginPoint()[0];


            // tieu chi bang cap
//            x(request) : bằng cấp của ứng viên
//            y : yêu cầu bằng cấp của việc làm
//            Nếu x >= y thì f = 1, ngược lại x < y thì f = 0;
            if (request.getDegreeId() >= job.getDegree().getId()) job.getOriginPoint()[1] = 1;
            sqrtSquaredSum[1] = sqrtSquaredSum[1] + job.getOriginPoint()[1] * job.getOriginPoint()[1];

            // tieu chi kinh nghiem
//            x(request) : kinh nghiệm của ứng viên
//            y : yêu cầu kinh nghiệm của việc làm
//            Nếu x>= y thì f = 1, ngược lại x < y thì f = x/5y;
            if (request.getExperience() >= job.getExperience()) job.getOriginPoint()[2] = 1;
            else job.getOriginPoint()[2] = (double) request.getExperience() / (5 * job.getExperience());
            sqrtSquaredSum[2] = sqrtSquaredSum[2] + job.getOriginPoint()[2] * job.getOriginPoint()[2];

            // tieu chi muc luong mong muon
//            avg = (min + max) /2
//            Nếu x <= max thì f = 1,
//            ngược lại x > max thì f =  avg/ x
            if (request.getExpectedOffer() <= job.getMaxOffer()) job.getOriginPoint()[3] = 1;
            else {
                double avgOffer = (double) (job.getMinOffer() + job.getMaxOffer()) / 2;
                job.getOriginPoint()[3] = avgOffer / request.getExpectedOffer();
            }
            sqrtSquaredSum[3] = sqrtSquaredSum[3] + job.getOriginPoint()[3] * job.getOriginPoint()[3];
        }
        for (int j = 0; j < 4; j++)
            sqrtSquaredSum[j] = Math.sqrt(sqrtSquaredSum[j]);

        // Chuẩn hóa ma trận quyết định
        for (int j = 0; j < 4; j++) {
            if (sqrtSquaredSum[j] != 0) {
                for (JobResponse job : jobs) {
                    job.getNormalPoint()[j] = job.getOriginPoint()[j] / sqrtSquaredSum[j];
                }
            }
        }
        // Tạo ma trận trọng số chuẩn hóa
        double[] weight = new double[4];
        int totalWeight = request.getMajorWeight() + request.getDegreeWeight() + request.getExperienceWeight() + request.getOfferWeight();
        weight[0] = (double)request.getMajorWeight() / totalWeight;
        weight[1] = (double)request.getDegreeWeight() / totalWeight;
        weight[2] = (double)request.getExperienceWeight() / totalWeight;
        weight[3] = (double)request.getOfferWeight() / totalWeight;
        for (int j = 0; j < 4; j++) {
            for (JobResponse job : jobs) {
                job.getWeightPoint()[j] = job.getNormalPoint()[j] * weight[j];
            }
        }
        //Xác định giải pháp lý tưởng tốt nhất (A*) và giải pháp lý tưởng tệ nhất (A-)
        double[] bestSolution = new double[4];
        double[] worstSolution = {1, 1, 1, 1};
        for (int j = 0; j < 4; j++) {
            for (JobResponse job : jobs) {
                bestSolution[j] = Math.max(bestSolution[j], job.getWeightPoint()[j]);
                worstSolution[j] = Math.min(worstSolution[j], job.getWeightPoint()[j]);
            }
        }
        //Tính khoảng cách đến giải pháp lý tưởng tốt nhất và tệ nhất
        //Tính chỉ số tương đồng tới giải pháp lý tưởng
        for (JobResponse job : jobs) {
            double squareSumBest = 0;
            double squareSumWorst = 0;
            for (int j = 0; j < 4; j++) {
                squareSumBest += (job.getWeightPoint()[j] - bestSolution[j]) * (job.getWeightPoint()[j] - bestSolution[j]);
                squareSumWorst += (job.getWeightPoint()[j] - worstSolution[j]) * (job.getWeightPoint()[j] - worstSolution[j]);
            }
            job.setDistanceBest(Math.sqrt(squareSumBest));
            job.setDistanceWorst(Math.sqrt(squareSumWorst));
            if (job.getDistanceBest() + job.getDistanceWorst() != 0)
                job.setP(job.getDistanceWorst() / (job.getDistanceBest() + job.getDistanceWorst()));
            else job.setP(1);
        }

//        jobs.sort(new Comparator<JobResponse>() {
//            @Override
//            public int compare(JobResponse j1, JobResponse j2) {
//                return Double.compare(j2.getP(), j1.getP());
//            }
//        });


        TopsisSearchJobResponse response = new TopsisSearchJobResponse();
        response.setJobs(jobs);
        response.setWeight(weight);
        response.setBestSolution(bestSolution);
        response.setWorstSolution(worstSolution);


        return response;
    }

    public Job getById(Integer id) {
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

    public void delete(Integer jobId) {
        jobRepository.deleteById(jobId);
    }

}
