package com.example.aventusbackend.dto.request;

import com.example.aventusbackend.validator.DeadlineConstraint;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobRequest {
    @NotEmpty(message = "EMPTY_JOB_NAME")
    String name;
//    @NotNull(message = "NULL_CAREER")
    int careerId;
//    @NotNull(message = "NULL_REQUIRE_DEGREE")
    int degreeId;
//    @NotNull(message = "NULL_REQUIRE_EXPERIENCE")
    int experience;
//    @NotNull(message = "NULL_MIN_OFFER")
    int minOffer;
//    @NotEmpty(message = "")
    int maxOffer;
    @NotEmpty(message = "EMPTY_JOB_DESCRIPTION")
    String description;
    @NotEmpty(message = "EMPTY_JOB_REQUIREMENT")
    String requirement;
    @NotEmpty(message = "EMPTY_JOB_BENEFIT")
    String benefit;

    int quantity;
    @NotEmpty(message = "EMPTY_WARD")
    String ward_code;
    @NotEmpty(message = "EMPTY_ADDRESS")
    String address;
    @DeadlineConstraint(message = "INVALID_DEADLINE")
    LocalDate deadline;

}
