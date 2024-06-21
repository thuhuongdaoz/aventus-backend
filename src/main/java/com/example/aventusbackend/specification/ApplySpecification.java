package com.example.aventusbackend.specification;

import com.example.aventusbackend.entity.Apply;
import com.example.aventusbackend.entity.Job;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ApplySpecification {
    public static Specification<Apply> hasEmployerId(Integer employerId) {
        return (root, query, criteriaBuilder) ->
                employerId == null ? null : criteriaBuilder.equal(root.get("job").get("employer").get("id"), employerId);
    }
    public static Specification<Apply> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }
    public static Specification<Apply> hasMajorId(Integer majorId) {
        return (root, query, criteriaBuilder) ->
                majorId == null ? null : criteriaBuilder.equal(root.get("major").get("id"), majorId);
    }
    public static Specification<Apply> hasDegreeId(Integer degreeId) {
        return (root, query, criteriaBuilder) ->
                degreeId == null ? null : criteriaBuilder.equal(root.get("degree").get("id"), degreeId);
    }

    public static Specification<Apply> hasExperience(Integer experience) {
        return (root, query, criteriaBuilder) ->
                    experience == null ? null : criteriaBuilder.equal(root.get("experience"), experience);
    }

    public static Specification<Apply> hasEnglishLevelId(Integer englishLevelId) {
        return (root, query, criteriaBuilder) ->
                englishLevelId == null ? null : criteriaBuilder.equal(root.get("englishLevel").get("id"), englishLevelId);
    }
    public static Specification<Apply> hasStatus(Integer applicationStatus) {
        return (root, query, criteriaBuilder) ->
                applicationStatus == null ? null : criteriaBuilder.equal(root.get("status"), applicationStatus);
    }





}
