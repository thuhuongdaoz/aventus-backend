package com.example.aventusbackend.controller;

import com.example.aventusbackend.dto.request.ChangePasswordRequest;
import com.example.aventusbackend.dto.response.ChangePasswordResponse;
import com.example.aventusbackend.dto.response.UserResponse;
import com.example.aventusbackend.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    UserService userService;
    @GetMapping("/myInfo")
    UserResponse getMyInfo(){
        return userService.getMyInfo();
    }

    @PostMapping("/change-password")
    public ChangePasswordResponse changePassword(@Valid  @RequestBody ChangePasswordRequest request) {
        return  userService.changePassword(request);
    }


}
