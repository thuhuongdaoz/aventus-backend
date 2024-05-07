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
    String avatar;
    String address;
    @ManyToOne
    @JoinColumn(name="ward_code")
    Ward ward;

    @ManyToOne
    @JoinColumn(name="major_id")
    Major major;

    int degree;
    double englishLevel;




}
