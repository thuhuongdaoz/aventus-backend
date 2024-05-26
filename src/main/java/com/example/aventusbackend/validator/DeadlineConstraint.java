package com.example.aventusbackend.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { DeadlineValidator.class })
public @interface DeadlineConstraint {
    String message() default "Invalid deadline";


    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
