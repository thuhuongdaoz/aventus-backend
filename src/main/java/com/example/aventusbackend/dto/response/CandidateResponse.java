package com.example.aventusbackend.dto.response;

import com.example.aventusbackend.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CandidateResponse {
    String id;
    String email;
    String name;
    Role role;
}
