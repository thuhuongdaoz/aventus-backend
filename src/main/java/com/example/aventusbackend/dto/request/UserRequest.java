package com.example.aventusbackend.dto.request;

import com.example.aventusbackend.validator.DobConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    @NotEmpty(message = "EMPTY_NAME")
    String name;

    @DobConstraint(message = "INVALID_DOB")
    LocalDate dateOfBirth;
    String phoneNumber;
    String avatar;

}
