package com.example.aventusbackend.dto.response;

import com.example.aventusbackend.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    int id;
    String email;
    String name;
    Role role;
    String avatar;
}
