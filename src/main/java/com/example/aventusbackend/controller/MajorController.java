package com.example.aventusbackend.controller;

import com.example.aventusbackend.dto.response.WardResponse;
import com.example.aventusbackend.entity.Major;
import com.example.aventusbackend.service.MajorService;
import com.example.aventusbackend.service.WardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/majors")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class MajorController {
    MajorService majorService;

    @GetMapping
    List<Major> getAll(){
        return majorService.getAll();

    }

}
