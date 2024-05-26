package com.example.aventusbackend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CandidateCreateRequest {
    @Email(message = "INVALID_EMAIL", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "EMPTY_EMAIL")
    String email;

    @NotEmpty(message = "EMPTY_PASSWORD")
    String password;
    @NotEmpty(message = "EMPTY_NAME")
    String name;

}
