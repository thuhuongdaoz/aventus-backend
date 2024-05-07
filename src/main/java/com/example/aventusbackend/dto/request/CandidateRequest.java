package com.example.aventusbackend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;


@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CandidateRequest extends UserRequest {


}
