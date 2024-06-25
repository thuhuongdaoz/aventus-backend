package com.example.aventusbackend.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TopsisSearchApplyResponse {
    List<ApplyResponse> applies;
    double[] weight;
    double[] bestSolution;
    double[] worstSolution;
}
