package com.example.aventusbackend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    //    @ManyToOne
//    @JoinColumn(name="major_group_id")
//    MajorGroup majorGroup;
    @OneToMany(mappedBy = "major")
    @JsonManagedReference
    Set<MajorCareer> majorCareers;
}
