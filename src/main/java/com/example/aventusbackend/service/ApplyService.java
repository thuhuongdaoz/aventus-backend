package com.example.aventusbackend.service;

import com.example.aventusbackend.dto.request.ChangeStatusRequest;
import com.example.aventusbackend.dto.request.TopsisSearchApplyRequest;
import com.example.aventusbackend.dto.request.TopsisSearchJobRequest;
import com.example.aventusbackend.dto.response.*;
import com.example.aventusbackend.entity.*;
import com.example.aventusbackend.exception.AppException;
import com.example.aventusbackend.exception.ErrorCode;
import com.example.aventusbackend.mapper.ApplyMapper;
import com.example.aventusbackend.mapper.WardMapper;
import com.example.aventusbackend.repository.ApplyRepository;
import com.example.aventusbackend.repository.DistrictRepository;
import com.example.aventusbackend.repository.WardRepository;
import com.example.aventusbackend.specification.ApplySpecification;
import com.example.aventusbackend.specification.JobSpecification;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplyService {
    ApplyRepository applyRepository;

    ApplyMapper applyMapper;

    public boolean hasCandidateAppliedForJob(Integer candidateId, Integer jobId) {
        return applyRepository.existsByCandidateIdAndJobId(candidateId, jobId);
    }

    public List<Apply> search(Integer employerId, Integer jobId,  String name, Integer majorId, Integer degreeId, Integer experience, Integer englishLevelId, Integer applicationStatus) {
        Specification<Apply> spec = Specification.where(ApplySpecification.hasEmployerId(employerId))
                .and(ApplySpecification.hasJobId(jobId))
                .and(ApplySpecification.hasName(name))
                .and(ApplySpecification.hasMajorId(majorId))
                .and(ApplySpecification.hasDegreeId(degreeId))
                .and(ApplySpecification.hasExperience(experience))
                .and(ApplySpecification.hasEnglishLevelId(englishLevelId))
                .and(ApplySpecification.hasStatus(applicationStatus));

        return applyRepository.findAll(spec);

    }
    public TopsisSearchApplyResponse topsisSearch(TopsisSearchApplyRequest request) {
        Specification<Apply> spec = Specification.where(ApplySpecification.hasEmployerId(request.getEmployerId()))
                .and(ApplySpecification.hasJobId(request.getJobId()));

        List<ApplyResponse> applies = new ArrayList<>(applyRepository.findAll(spec).stream().map(applyMapper::toApplyResponse).toList());

        //Xây dựng ma trận quyết định
        double[] sqrtSquaredSum = new double[3];
        for (ApplyResponse apply : applies) {
            apply.setOriginPoint(new double[3]);
            apply.setNormalPoint(new double[3]);
            apply.setWeightPoint(new double[3]);
//            apply.setPoint(new double[3]);

            // tieu chi nganh nghe
            for (MajorCareer majorCareer : apply.getMajor().getMajorCareers()) {
                if (majorCareer.getCareer().getId() == request.getCareerId()) {
                    apply.getOriginPoint()[0] = majorCareer.getPoint();
                    break;
                }
            }
            sqrtSquaredSum[0] = sqrtSquaredSum[0] + apply.getOriginPoint()[0] * apply.getOriginPoint()[0];


            // tieu chi bang cap
//            x : bằng cấp của ứng viên
//            y(request) : yêu cầu bằng cấp của nhà tuyển dụng
//            Nếu x >= y thì f = 1, ngược lại x < y thì f = 0;
            if (apply.getDegree().getId() >= request.getDegreeId()) apply.getOriginPoint()[1] = 1;
            sqrtSquaredSum[1] = sqrtSquaredSum[1] + apply.getOriginPoint()[1] * apply.getOriginPoint()[1];

            // tieu chi kinh nghiem
//            x : kinh nghiệm của ứng viên
//            y(request) : yêu cầu kinh nghiệm của nhà tuyển dụng
//            Nếu x>= y thì f = 1, ngược lại x < y thì f = x/5y;
            if (apply.getExperience() >= request.getExperience()) apply.getOriginPoint()[2] = 1;
            else apply.getOriginPoint()[2] = (double) apply.getExperience() / (5 * request.getExperience() );
            sqrtSquaredSum[2] = sqrtSquaredSum[2] + apply.getOriginPoint()[2] * apply.getOriginPoint()[2];
        }
        for (int j = 0; j < 3; j++)
            sqrtSquaredSum[j] = Math.sqrt(sqrtSquaredSum[j]);

        // Chuẩn hóa ma trận quyết định
        for (int j = 0; j < 3; j++) {
            if (sqrtSquaredSum[j] != 0) {
                for (ApplyResponse apply : applies) {
                    apply.getNormalPoint()[j] = apply.getOriginPoint()[j] / sqrtSquaredSum[j];
                }
            }
        }
        // Tạo ma trận trọng số chuẩn hóa
        double[] weight = new double[3];
        int totalWeight = request.getCareerWeight() + request.getDegreeWeight() + request.getExperienceWeight();
        weight[0] = (double)request.getCareerWeight() / totalWeight;
        weight[1] = (double)request.getDegreeWeight() / totalWeight;
        weight[2] = (double)request.getExperienceWeight() / totalWeight;
        for (int j = 0; j < 3; j++) {
            for (ApplyResponse apply : applies) {
                apply.getWeightPoint()[j] = apply.getNormalPoint()[j] * weight[j];
            }
        }
        // Xác định giải pháp lý tưởng tốt nhất (A*) và giải pháp lý tưởng tệ nhất (A-)
        double[] bestSolution = new double[3];
        double[] worstSolution = {1, 1, 1};
        for (int j = 0; j < 3; j++) {
            for (ApplyResponse apply : applies) {
                bestSolution[j] = Math.max(bestSolution[j], apply.getWeightPoint()[j]);
                worstSolution[j] = Math.min(worstSolution[j], apply.getWeightPoint()[j]);
            }
        }
        //Tính khoảng cách đến giải pháp lý tưởng tốt nhất và tệ nhất
        //Tính chỉ số tương đồng tới giải pháp lý tưởng
        for (ApplyResponse apply : applies) {
            double squareSumBest = 0;
            double squareSumWorst = 0;
            for (int j = 0; j < 3; j++) {
                squareSumBest += (apply.getWeightPoint()[j] - bestSolution[j]) * (apply.getWeightPoint()[j] - bestSolution[j]);
                squareSumWorst += (apply.getWeightPoint()[j] - worstSolution[j]) * (apply.getWeightPoint()[j] - worstSolution[j]);
            }
            apply.setDistanceBest(Math.sqrt(squareSumBest));
            apply.setDistanceWorst(Math.sqrt(squareSumWorst));
            if(apply.getDistanceBest() + apply.getDistanceWorst() != 0)
                apply.setP(apply.getDistanceWorst()/ (apply.getDistanceBest() + apply.getDistanceWorst()));
            else apply.setP(1);
        }

//        applies.sort(new Comparator<ApplyResponse>() {
//            @Override
//            public int compare(ApplyResponse j1, ApplyResponse j2) {
//                return Double.compare(j2.getP(), j1.getP());
//            }
//        });
        TopsisSearchApplyResponse response = new TopsisSearchApplyResponse();
        response.setApplies(applies);
        response.setWeight(weight);
        response.setBestSolution(bestSolution);
        response.setWorstSolution(worstSolution);
        return response;
    }
    public Apply getById(Integer id) {
        return   applyRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.APPLY_NOT_EXISTED));

    }
    public boolean changeStatus(Integer id, ChangeStatusRequest request){
        Apply apply = applyRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.APPLY_NOT_EXISTED));
        apply.setStatus(request.getStatus());
        applyRepository.save(apply);
        return true;


    }
}
