package com.example.aventusbackend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TopsisSearchApplyRequest {

    Integer employerId;
    Integer jobId;
    @NotNull(message = "NULL_CAREER")
    Integer careerId;
    @NotNull(message = "NULL_DEGREE")
    Integer degreeId;
    @NotNull(message = "NULL_EXPERIENCE")
    Integer experience;
    @NotNull(message = "NULL_CAREER_WEIGHT")
    Integer careerWeight;
    @NotNull(message = "NULL_DEGREE_WEIGHT")
    Integer degreeWeight;
    @NotNull(message = "NULL_EXPERIENCE_WEIGHT")
    Integer experienceWeight;



}
