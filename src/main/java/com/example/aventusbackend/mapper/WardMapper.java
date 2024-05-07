package com.example.aventusbackend.mapper;

import com.example.aventusbackend.dto.response.DistrictResponse;
import com.example.aventusbackend.dto.response.WardResponse;
import com.example.aventusbackend.entity.District;
import com.example.aventusbackend.entity.Ward;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WardMapper {
//    Employer toEmployer(EmployerRequest request);

//    @Mapping(target = "province", source = "district.province.code")
    WardResponse toWardResponse(Ward ward);
}
