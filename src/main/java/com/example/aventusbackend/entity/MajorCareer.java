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
public class MajorCareer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "major_id")
    @JsonBackReference
    Major major;

    @ManyToOne
    @JoinColumn(name = "career_id")
    @JsonBackReference
    Career career;

    double point;
}
