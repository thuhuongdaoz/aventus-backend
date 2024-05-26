package com.example.aventusbackend.dto.request;

import com.example.aventusbackend.entity.Degree;
import com.example.aventusbackend.entity.EnglishLevel;
import com.example.aventusbackend.entity.Major;
import com.example.aventusbackend.entity.Ward;
import com.example.aventusbackend.validator.DobConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CandidateUpdateRequest {
    @NotEmpty(message = "EMPTY_NAME")
    String name;

    @DobConstraint(message = "INVALID_DOB")
    LocalDate dateOfBirth;
//    @Pattern(regexp="[0-9]{10}", message="INVALID_PHONE_NUMBER")
    String phoneNumber;

    String avatar;

    String ward_code;
    String address;

    int major_id;
    int degree_id;
    int english_level_id;

}
