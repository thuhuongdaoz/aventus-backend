package com.example.aventusbackend.specification;

import com.example.aventusbackend.entity.Job;
import com.example.aventusbackend.entity.User;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class UserSpecification {

    public static Specification<User> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }
    public static Specification<User> hasEmail(String email) {
        return (root, query, criteriaBuilder) ->
                email == null ? null : criteriaBuilder.like(root.get("email"), "%" + email + "%");
    }
    public static Specification<User> hasRoleName(String roleName) {
        return (root, query, criteriaBuilder) ->
                roleName == null ? null : criteriaBuilder.equal(root.get("role").get("name"), roleName);
    }
    public static Specification<User> hasPhoneNumber(String phoneNumber) {
        return (root, query, criteriaBuilder) ->
                phoneNumber == null ? null : criteriaBuilder.like(root.get("phoneNumber"), "%" + phoneNumber + "%");
    }
    public static Specification<User> hasStatus(Integer userStatus) {
        return (root, query, criteriaBuilder) ->
                userStatus == null ? null : criteriaBuilder.equal(root.get("status"), userStatus);
    }


}
