package com.example.aventusbackend.dto.response;

import com.example.aventusbackend.entity.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplyResponse {
    int id;
    Candidate candidate;
    Job job;

    String name;
    String phoneNumber;

    Major major;

    Degree degree;

    int experience;
    EnglishLevel englishLevel;

    String resume;

    int status;
    double[] originPoint;
    double[] normalPoint;
    double[] weightPoint;
//    double[] point;
    double distanceBest;
    double distanceWorst;
    double p;
}
