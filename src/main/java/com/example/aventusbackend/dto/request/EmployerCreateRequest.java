package com.example.aventusbackend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployerCreateRequest {
    @Email(message = "INVALID_EMAIL", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "EMPTY_EMAIL")
    String email;

    @NotEmpty(message = "EMPTY_PASSWORD")
    String password;
    @NotEmpty(message = "EMPTY_NAME")
    String name;

    @NotEmpty(message = "EMPTY_COMPANY_NAME")
    String companyName;
    @NotEmpty(message = "EMPTY_ADDRESS")
    String address;
    @NotEmpty(message = "EMPTY_WARD")
    String ward_code;
}
