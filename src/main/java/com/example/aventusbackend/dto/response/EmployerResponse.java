package com.example.aventusbackend.dto.response;

import com.example.aventusbackend.entity.Role;
import com.example.aventusbackend.entity.Ward;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployerResponse {
    String id;
    String email;
    String name;
    Role role;
    LocalDate dateOfBirth;
    String phoneNumber;
    String avatar;
    int status;

    String companyName;
    Ward ward;
    String address;
    String picture;
}
