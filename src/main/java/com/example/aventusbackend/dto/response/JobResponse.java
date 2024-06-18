package com.example.aventusbackend.dto.response;

import com.example.aventusbackend.entity.Career;
import com.example.aventusbackend.entity.Degree;
import com.example.aventusbackend.entity.Employer;
import com.example.aventusbackend.entity.Ward;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobResponse {
    int id;
    Employer employer;

    String name;

    Career career;

    Degree degree;

    int experience;

    int minOffer;
    int maxOffer;

    String description;
    String requirement;
    String benefit;

    int quantity;

    Ward ward;

    String address;

    LocalDate deadline;
    double[] point;
    double distanceBest;
    double distanceWorst;
    double p;
}
