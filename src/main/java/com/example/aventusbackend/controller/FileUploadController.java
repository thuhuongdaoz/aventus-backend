package com.example.aventusbackend.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/upload")
public class FileUploadController {
    @Value("${server.upload.dir}")
    private String uploadDir;

    @PostConstruct
    public void init() {
        Path path = Paths.get(uploadDir);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @PostMapping("/avatar")
    public ResponseEntity<String> uploadAvatar(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
            }
            String subdir = "avatar/";
            // Create the directory if it does not exist
            Path subdirPath = Paths.get(uploadDir + subdir);
            if (!Files.exists(subdirPath)) {
                Files.createDirectories(subdirPath);
            }
            String filename = file.getOriginalFilename();
            Path path = Paths.get(uploadDir + subdir + filename);


            Files.write(path, file.getBytes());

            String fileUrl = "/upload/avatar/" + filename;
            return ResponseEntity.status(HttpStatus.OK).body(fileUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }

    @PostMapping("/picture")
    public ResponseEntity<String> uploadPicture(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
            }
            String subdir = "picture/";
            // Create the directory if it does not exist
            Path subdirPath = Paths.get(uploadDir + subdir);
            if (!Files.exists(subdirPath)) {
                Files.createDirectories(subdirPath);
            }
            String filename = file.getOriginalFilename();
            Path path = Paths.get(uploadDir + subdir + filename);


            Files.write(path, file.getBytes());

            String fileUrl = "/upload/picture/" + filename;
            return ResponseEntity.status(HttpStatus.OK).body(fileUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }


}
