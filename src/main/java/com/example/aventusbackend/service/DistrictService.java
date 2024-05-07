package com.example.aventusbackend.service;

import com.example.aventusbackend.dto.response.DistrictResponse;
import com.example.aventusbackend.entity.District;
import com.example.aventusbackend.entity.Province;
import com.example.aventusbackend.entity.Ward;
import com.example.aventusbackend.exception.AppException;
import com.example.aventusbackend.exception.ErrorCode;
import com.example.aventusbackend.mapper.DistrictMapper;
import com.example.aventusbackend.repository.DistrictRepository;
import com.example.aventusbackend.repository.ProvinceRepository;
import com.example.aventusbackend.repository.WardRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DistrictService {
    DistrictRepository districtRepository;
    ProvinceRepository provinceRepository;
    DistrictMapper districtMapper;
    public List<District> getAll(){
        return districtRepository.findAll();
    }

    public List<DistrictResponse> getDistrictsByProvince(String provinceCode) {
        Province province = provinceRepository.findById(provinceCode).orElseThrow(() -> new AppException(ErrorCode.INVALID_PROVINCE));
        return districtRepository
                .findByProvince(province).stream()
                .map(districtMapper::toDistrictResponse).toList();
    }


}
