package com.example.aventusbackend.exception;

import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.Objects;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final String MIN_ATTRIBUTE = "min";

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<String> handlingAppException(AppException exception){
        ErrorCode errorCode = exception.getErrorCode();
        String message = errorCode.getMessage();

        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(message);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<String> handlingValidation(MethodArgumentNotValidException exception){
        String enumKey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(enumKey);
//        var constraintViolation = exception.getBindingResult()
//                .getAllErrors().getFirst().unwrap(ConstraintViolation.class);
//
//        Map<String, Object> attributes = constraintViolation.getConstraintDescriptor().getAttributes();
//        log.info(attributes.toString());
//
//        String message = Objects.nonNull(attributes) ?
//                mapAttribute(errorCode.getMessage(), attributes)
//                : errorCode.getMessage();
        String message = errorCode.getMessage();
        return ResponseEntity.badRequest().body(message);
    }

    private String mapAttribute(String message, Map<String, Object> attributes){
        String minValue = String.valueOf(attributes.get(MIN_ATTRIBUTE));

        return message.replace("{" + MIN_ATTRIBUTE + "}", minValue);
    }
}
