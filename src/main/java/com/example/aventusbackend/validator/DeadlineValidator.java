package com.example.aventusbackend.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.util.Objects;

public class DeadlineValidator implements ConstraintValidator<DeadlineConstraint, LocalDate> {


    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (Objects.isNull(value))
            return true;

        return !value.isBefore(LocalDate.now());
    }

    @Override
    public void initialize(DeadlineConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
