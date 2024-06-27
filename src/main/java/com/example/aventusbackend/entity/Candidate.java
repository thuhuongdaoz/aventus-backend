package com.example.aventusbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    Integer experience;

    @ManyToOne
    @JoinColumn(name="english_level_id")
    EnglishLevel englishLevel;

    @OneToMany(mappedBy = "candidate")
//    @JsonManagedReference
    @JsonIgnore
    Set<Apply> applies;





}
