package com.example.aventusbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class User {
    @Id
    @GeneratedValue
    int id;
    String email;
    String password;
    String name;
    @ManyToOne
    @JoinColumn(name="role_name", nullable=false)
    Role role;


    LocalDate dateOfBirth;
    String phoneNumber;
    String avatar;

    int status;
}
