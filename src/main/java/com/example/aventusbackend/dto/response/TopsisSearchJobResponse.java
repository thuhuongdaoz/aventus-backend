package com.example.aventusbackend.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TopsisSearchJobResponse {
    List<JobResponse> jobs;
    double[] weight;
    double[] bestSolution;
    double[] worstSolution;
}
