package com.example.aventusbackend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TopsisSearchApplyRequest {

    @NotNull(message = "NULL_CAREER")
    Integer careerId;
    @NotNull(message = "NULL_DEGREE")
    Integer degreeId;
    @NotNull(message = "NULL_EXPERIENCE")
    Integer experience;


}
