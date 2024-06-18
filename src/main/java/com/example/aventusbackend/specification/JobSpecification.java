package com.example.aventusbackend.specification;

import com.example.aventusbackend.entity.Job;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class JobSpecification {
    public static Specification<Job> hasEmployerId(Integer employerId) {
        return (root, query, criteriaBuilder) ->
                employerId == null ? null : criteriaBuilder.equal(root.get("employer").get("id"), employerId);
    }
    public static Specification<Job> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }
    public static Specification<Job> hasCareerId(Integer careerId) {
        return (root, query, criteriaBuilder) ->
                careerId == null ? null : criteriaBuilder.equal(root.get("career").get("id"), careerId);
    }
    public static Specification<Job> hasDegreeId(Integer degreeId) {
        return (root, query, criteriaBuilder) ->
                degreeId == null ? null : criteriaBuilder.equal(root.get("degree").get("id"), degreeId);
    }

    public static Specification<Job> hasExperience(Integer experience) {
        return (root, query, criteriaBuilder) ->
                    experience == null ? null : criteriaBuilder.equal(root.get("experience"), experience);
    }

    public static Specification<Job> hasMinOfferAndMaxOffer(Integer offer) {
        return (root, query, criteriaBuilder) ->
                offer == null ? null : criteriaBuilder.and(
                        criteriaBuilder.lessThanOrEqualTo(root.get("minOffer"), offer),
                        criteriaBuilder.greaterThanOrEqualTo(root.get("maxOffer"), offer)
                );
    }
    public static Specification<Job> isNotExpired() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("deadline"), LocalDate.now());
    }


}
