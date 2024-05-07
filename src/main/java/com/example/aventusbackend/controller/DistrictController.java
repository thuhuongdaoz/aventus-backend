package com.example.aventusbackend.controller;

import com.example.aventusbackend.dto.response.DistrictResponse;
import com.example.aventusbackend.entity.District;
import com.example.aventusbackend.entity.Province;
import com.example.aventusbackend.entity.Ward;
import com.example.aventusbackend.service.DistrictService;
import com.example.aventusbackend.service.ProvinceService;
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
@RequestMapping("/districts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class DistrictController {
    DistrictService districtService;
    ProvinceService provinceService;

//    @GetMapping
//    List<District> getAll(){
//        return districtService.getAll();
//
//    }
    @GetMapping
    public List<DistrictResponse> getDistrictsByProvince(@RequestParam("province") String provinceCode) {
        return districtService.getDistrictsByProvince(provinceCode);
    }
}
