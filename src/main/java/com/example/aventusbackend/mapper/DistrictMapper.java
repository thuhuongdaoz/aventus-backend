package com.example.aventusbackend.mapper;

import com.example.aventusbackend.dto.response.DistrictResponse;
import com.example.aventusbackend.dto.response.ProvinceResponse;
import com.example.aventusbackend.entity.District;
import com.example.aventusbackend.entity.Province;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DistrictMapper {
//    Employer toEmployer(EmployerRequest request);
//    @Mapping(target = "province", source = "district.province.code")
    DistrictResponse toDistrictResponse(District district);
}
