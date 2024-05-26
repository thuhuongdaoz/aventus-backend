package com.example.aventusbackend.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UploadController {
//    @PostMapping("/avatar")
//    public ResponseEntity<String> handleAvatarUpload(@RequestParam("avatar") MultipartFile avatarFile) {
//        // Handle avatar upload logic here, e.g., save the avatar file to disk or database
//        // You can access the file via 'avatarFile' parameter
//        return ResponseEntity.ok("Avatar uploaded successfully.");
//    }
}
