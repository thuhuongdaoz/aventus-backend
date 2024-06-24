package com.example.aventusbackend.controller;

import com.example.aventusbackend.dto.request.CandidateUpdateRequest;
import com.example.aventusbackend.dto.request.ChangePasswordRequest;
import com.example.aventusbackend.dto.request.ChangeStatusRequest;
import com.example.aventusbackend.dto.request.UserRequest;
import com.example.aventusbackend.dto.response.CandidateResponse;
import com.example.aventusbackend.dto.response.ChangePasswordResponse;
import com.example.aventusbackend.dto.response.UserResponse;
import com.example.aventusbackend.entity.Job;
import com.example.aventusbackend.entity.User;
import com.example.aventusbackend.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    List<User> search(@RequestParam(required = false) String name,
                      @RequestParam(required = false) String email,
                      @RequestParam(required = false) String roleName,
                      @RequestParam(required = false) String phoneNumber,
                      @RequestParam(required = false) Integer userStatus
                     ){

        return userService.search( name, email, roleName, phoneNumber, userStatus);
    }
    @PutMapping("/myInfo")
    UserResponse updateMyInfo(@RequestBody @Valid UserRequest request){
        return userService.updateMyInfo(request);

    }
    @PostMapping("/{userId}/changeStatus")
    boolean changeStatus(@PathVariable("userId") Integer userId){
        return userService.changeStatus(userId);
    }


}
