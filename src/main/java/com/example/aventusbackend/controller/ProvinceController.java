package com.example.aventusbackend.controller;

import com.example.aventusbackend.dto.response.ProvinceResponse;
import com.example.aventusbackend.entity.District;
import com.example.aventusbackend.entity.Province;
import com.example.aventusbackend.service.DistrictService;
import com.example.aventusbackend.service.ProvinceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/provinces")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
//@CrossOrigin(origins = "http://localhost:3000")
public class ProvinceController {
    ProvinceService provinceService;

    @GetMapping
    List<ProvinceResponse> getAll(){
        return provinceService.getAll();
    }
}
