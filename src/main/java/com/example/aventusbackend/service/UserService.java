package com.example.aventusbackend.service;

import com.example.aventusbackend.dto.request.ChangePasswordRequest;
import com.example.aventusbackend.dto.response.ChangePasswordResponse;
import com.example.aventusbackend.dto.response.UserResponse;
import com.example.aventusbackend.entity.User;
import com.example.aventusbackend.exception.AppException;
import com.example.aventusbackend.exception.ErrorCode;
import com.example.aventusbackend.mapper.UserMapper;
import com.example.aventusbackend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    UserMapper userMapper;
    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }
    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));
        // Check if the current password matches
        boolean authenticated = passwordEncoder.matches(request.getOldPassword(),
                user.getPassword());
        if (!authenticated)
            throw new AppException(ErrorCode.WRONG_PASSWORD);
        String encodedPassword = passwordEncoder.encode(request.getNewPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return ChangePasswordResponse.builder()
                .message("Đổi mật khẩu thành công")
                .build();
    }


    public boolean changePassword(User user, String currentPassword, String newPassword) {
        // Check if the current password matches
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            return false;
        }

        // Change the password
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        // Save or update the user entity

        return true;
    }
}
