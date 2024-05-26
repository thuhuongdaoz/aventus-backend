package com.example.aventusbackend.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangePasswordRequest {
    @NotEmpty(message = "EMPTY_OLD_PASSWORD")
    String oldPassword;
    @NotEmpty(message = "EMPTY_NEW_PASSWORD")
    String newPassword;
}
