package com.example.aventusbackend.dto.request;

import com.example.aventusbackend.entity.Degree;
import com.example.aventusbackend.entity.EnglishLevel;
import com.example.aventusbackend.entity.Major;
import com.example.aventusbackend.validator.DobConstraint;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplyRequest {
    @NotNull(message = "NULL_JOB")
    Integer jobId;
    @NotEmpty(message = "EMPTY_NAME")
    String name;
    String phoneNumber;
    @NotNull(message = "NULL_MAJOR")
    Integer majorId;
    @NotNull(message = "NULL_DEGREE")
    Integer degreeId;
    @NotNull(message = "NULL_EXPERIENCE")
    Integer experience;
    @NotNull(message = "NULL_EXPERIENCE")
    Integer englishLevelId;
    @NotNull(message = "EMPTY_RESUME")
    String resume;
}
