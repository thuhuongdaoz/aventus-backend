package com.example.aventusbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Candidate extends User {
    @ManyToOne
    @JoinColumn(name="ward_code")
    Ward ward;

    String address;


    @ManyToOne
    @JoinColumn(name="major_id")
    Major major;

    @ManyToOne
    @JoinColumn(name="degree_id")
    Degree degree;

    @ManyToOne
    @JoinColumn(name="english_level_id")
    EnglishLevel englishLevel;




}
