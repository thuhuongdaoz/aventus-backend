package com.example.aventusbackend.service;

import com.example.aventusbackend.dto.response.DistrictResponse;
import com.example.aventusbackend.dto.response.WardResponse;
import com.example.aventusbackend.entity.District;
import com.example.aventusbackend.entity.Province;
import com.example.aventusbackend.entity.Ward;
import com.example.aventusbackend.exception.AppException;
import com.example.aventusbackend.exception.ErrorCode;
import com.example.aventusbackend.mapper.WardMapper;
import com.example.aventusbackend.repository.DistrictRepository;
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
public class WardService {
    WardRepository wardRepository;
    DistrictRepository districtRepository;

    WardMapper wardMapper;

    public List<Ward> getAll(){
        return wardRepository.findAll();
    }

    public List<WardResponse> getWardsByDistrict(String districtCode) {
        District district = districtRepository.findById(districtCode).orElseThrow(() -> new AppException(ErrorCode.INVALID_PROVINCE));
        return wardRepository
                .findByDistrict(district).stream()
                .map(wardMapper::toWardResponse).toList();
    }
}
