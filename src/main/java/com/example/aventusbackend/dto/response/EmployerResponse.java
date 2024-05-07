package com.example.aventusbackend.dto.response;

import com.example.aventusbackend.entity.Role;
import com.example.aventusbackend.entity.Ward;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

    String companyName;
    String address;
    Ward ward;
}
