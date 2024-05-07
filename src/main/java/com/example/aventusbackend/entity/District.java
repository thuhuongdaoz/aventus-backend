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
public class District {
    @Id
    String code;
    String name;
    String full_name;
    @ManyToOne
    @JoinColumn(name="province_code", nullable=false)
    Province province;


}
