package com.example.aventusbackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
//    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    EMPTY_EMAIL(1001, "Email cannot be empty", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(1002, "Email is not valid", HttpStatus.BAD_REQUEST),
    EMPTY_PASSWORD(1003, "Password cannot be empty", HttpStatus.BAD_REQUEST),
    EMPTY_NAME(1004, "Name cannot be empty", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1005, "User existed", HttpStatus.BAD_REQUEST),
    EMPTY_COMPANY_NAME(1006, "Company name cannot be empty", HttpStatus.BAD_REQUEST),
    EMPTY_ADDRESS(1007, "Address cannot be empty", HttpStatus.BAD_REQUEST),
    EMPTY_WARD(1008, "Ward cannot be empty", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1009, "User not existed", HttpStatus.NOT_FOUND),
    WRONG_PASSWORD(1010, "Wrong password", HttpStatus.BAD_REQUEST),
    INVALID_PROVINCE(1011, "Invalid province", HttpStatus.BAD_REQUEST),
    INVALID_DISTRICT(1012, "Invalid district", HttpStatus.BAD_REQUEST),



    ;


    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;

}
