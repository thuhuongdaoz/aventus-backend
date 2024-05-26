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
public class ChangePasswordResponse {
    String message;
}
