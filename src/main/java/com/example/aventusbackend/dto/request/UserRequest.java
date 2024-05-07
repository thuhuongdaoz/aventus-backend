package com.example.aventusbackend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    @Email(message = "INVALID_EMAIL", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "EMPTY_EMAIL")
    String email;

    @NotEmpty(message = "EMPTY_PASSWORD")
    String password;
    @NotEmpty(message = "EMPTY_NAME")
    String name;
}
