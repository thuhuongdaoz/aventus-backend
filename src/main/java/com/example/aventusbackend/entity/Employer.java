package com.example.aventusbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Employer extends User {
    String companyName;
    String address;

    @ManyToOne
    @JoinColumn(name="ward_code")
    Ward ward;

    String pictureUrl;




}
