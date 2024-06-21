package com.example.aventusbackend.service;

import com.example.aventusbackend.dto.request.ChangeStatusRequest;
import com.example.aventusbackend.dto.request.TopsisSearchApplyRequest;
import com.example.aventusbackend.dto.request.TopsisSearchJobRequest;
import com.example.aventusbackend.dto.response.ApplyResponse;
import com.example.aventusbackend.dto.response.JobResponse;
import com.example.aventusbackend.dto.response.WardResponse;
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

    public List<Apply> search(Integer employerId, String name, Integer majorId, Integer degreeId, Integer experience, Integer englishLevelId, Integer applicationStatus) {
        Specification<Apply> spec = Specification.where(ApplySpecification.hasEmployerId(employerId))
                .and(ApplySpecification.hasName(name))
                .and(ApplySpecification.hasMajorId(majorId))
                .and(ApplySpecification.hasDegreeId(degreeId))
                .and(ApplySpecification.hasExperience(experience))
                .and(ApplySpecification.hasEnglishLevelId(englishLevelId))
                .and(ApplySpecification.hasStatus(applicationStatus));

        return applyRepository.findAll(spec);

    }
    public List<ApplyResponse> topsisSearch(TopsisSearchApplyRequest request) {
        List<ApplyResponse> applies = new ArrayList<>(applyRepository.findAll().stream().map(applyMapper::toApplyResponse).toList());

        //Xây dựng ma trận quyết định
        double[] sqrtSquaredSum = new double[3];
        for (ApplyResponse apply : applies) {
            apply.setPoint(new double[3]);

            // tieu chi nganh nghe
            for (MajorCareer majorCareer : apply.getMajor().getMajorCareers()) {
                if (majorCareer.getCareer().getId() == request.getCareerId()) {
                    apply.getPoint()[0] = majorCareer.getPoint();
                    break;
                }
            }
            sqrtSquaredSum[0] = sqrtSquaredSum[0] + apply.getPoint()[0] * apply.getPoint()[0];


            // tieu chi bang cap
//            x : bằng cấp của ứng viên
//            y(request) : yêu cầu bằng cấp của nhà tuyển dụng
//            Nếu x >= y thì f = 1, ngược lại x < y thì f = 0;
            if (apply.getDegree().getId() >= request.getDegreeId()) apply.getPoint()[1] = 1;
            sqrtSquaredSum[1] = sqrtSquaredSum[1] + apply.getPoint()[1] * apply.getPoint()[1];

            // tieu chi kinh nghiem
//            x : kinh nghiệm của ứng viên
//            y(request) : yêu cầu kinh nghiệm của nhà tuyển dụng
//            Nếu x>= y thì f = 1, ngược lại x < y thì f = x/5y;
            if (apply.getExperience() >= request.getExperience()) apply.getPoint()[2] = 1;
            else apply.getPoint()[2] = (double) apply.getExperience() / (5 * request.getExperience() );
            sqrtSquaredSum[2] = sqrtSquaredSum[2] + apply.getPoint()[2] * apply.getPoint()[2];
        }
        for (int j = 0; j < 3; j++)
            sqrtSquaredSum[j] = Math.sqrt(sqrtSquaredSum[j]);

        // Chuẩn hóa ma trận quyết định
        for (int j = 0; j < 3; j++) {
            if (sqrtSquaredSum[j] != 0) {
                for (ApplyResponse apply : applies) {
                    apply.getPoint()[j] = apply.getPoint()[j] / sqrtSquaredSum[j];
                }
            }
        }
        // Tạo ma trận trọng số chuẩn hóa va Xác định giải pháp lý tưởng tốt nhất (A*) và giải pháp lý tưởng tệ nhất (A-)
        double[] bestSolution = new double[3];
        double[] worstSolution = {1, 1, 1};
        for (int j = 0; j < 3; j++) {
            for (ApplyResponse apply : applies) {
                apply.getPoint()[j] = apply.getPoint()[j] * 0.25;
                bestSolution[j] = Math.max(bestSolution[j], apply.getPoint()[j]);
                worstSolution[j] = Math.min(worstSolution[j], apply.getPoint()[j]);
            }
        }
        //Tính khoảng cách đến giải pháp lý tưởng tốt nhất và tệ nhất
        //Tính chỉ số tương đồng tới giải pháp lý tưởng
        for (ApplyResponse apply : applies) {
            double squareSumBest = 0;
            double squareSumWorst = 0;
            for (int j = 0; j < 3; j++) {
                squareSumBest += (apply.getPoint()[j] - bestSolution[j]) * (apply.getPoint()[j] - bestSolution[j]);
                squareSumWorst += (apply.getPoint()[j] - worstSolution[j]) * (apply.getPoint()[j] - worstSolution[j]);
            }
            apply.setDistanceBest(Math.sqrt(squareSumBest));
            apply.setDistanceWorst(Math.sqrt(squareSumWorst));
            apply.setP(apply.getDistanceWorst()/ (apply.getDistanceBest() + apply.getDistanceWorst()));
        }

        applies.sort(new Comparator<ApplyResponse>() {
            @Override
            public int compare(ApplyResponse j1, ApplyResponse j2) {
                return Double.compare(j2.getP(), j1.getP());
            }
        });

        return applies;
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
