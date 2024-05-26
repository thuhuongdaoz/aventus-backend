package com.example.aventusbackend.dto.request;

import com.example.aventusbackend.validator.DobConstraint;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployerUpdateRequest {
    @NotEmpty(message = "EMPTY_NAME")
    String name;

    @DobConstraint(message = "INVALID_DOB")
    LocalDate dateOfBirth;
//    @Pattern(regexp="[0-9]{10}", message="INVALID_PHONE_NUMBER")
    String phoneNumber;
    String avatar;

    @NotEmpty(message = "EMPTY_COMPANY_NAME")
    String companyName;
    String ward_code;
    String address;
    String picture;

}
