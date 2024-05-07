package com.example.aventusbackend.dto.request;

import com.example.aventusbackend.entity.Ward;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployerRequest extends UserRequest {
    @NotEmpty(message = "EMPTY_COMPANY_NAME")
    String companyName;
    @NotEmpty(message = "EMPTY_ADDRESS")
    String address;
    @NotEmpty(message = "EMPTY_WARD")
    String ward;
}
