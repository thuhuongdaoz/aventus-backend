package com.example.aventusbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Ward {
    @Id
    String code;
    String name;
    String full_name;
    @ManyToOne
    @JoinColumn(name="district_code", nullable=false)
    District district;
}
