package com.example.aventusbackend.dto.request;

import com.example.aventusbackend.validator.DeadlineConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TopsisSearchJobRequest {

    @NotNull(message = "NULL_MAJOR")
    Integer majorId;
    @NotNull(message = "NULL_DEGREE")
    Integer degreeId;
    @NotNull(message = "NULL_EXPERIENCE")
    Integer experience;
    @NotNull(message = "NULL_EXPECTED_OFFER")
    Integer expectedOffer;
    @NotNull(message = "NULL_MAJOR_WEIGHT")
    Integer majorWeight;
    @NotNull(message = "NULL_DEGREE_WEIGHT")
    Integer degreeWeight;
    @NotNull(message = "NULL_EXPERIENCE_WEIGHT")
    Integer experienceWeight;
    @NotNull(message = "NULL_OFFER_WEIGHT")
    Integer offerWeight;



}
