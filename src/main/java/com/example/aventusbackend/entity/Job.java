package com.example.aventusbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Job {
    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    Employer employer;

    String name;

    @ManyToOne
    @JoinColumn(name = "career_id")
    Career career;

    @ManyToOne
    @JoinColumn(name = "degree_id")
    Degree degree;

    int experience;

    int minOffer;
    int maxOffer;

    String description;
    String requirement;
    String benefit;

    int quantity;

    @ManyToOne
    @JoinColumn(name = "ward_code")
    Ward ward;

    String address;

    LocalDate deadline;




}
