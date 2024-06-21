package com.example.aventusbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
//    @JsonBackReference
    Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    @JsonBackReference
    Job job;

    String name;
    String phoneNumber;
    @ManyToOne
    @JoinColumn(name="major_id")
    Major major;

    @ManyToOne
    @JoinColumn(name="degree_id")
    Degree degree;

    int experience;

    @ManyToOne
    @JoinColumn(name="english_level_id")
    EnglishLevel englishLevel;

    String resume;

    int status = 1;

}
