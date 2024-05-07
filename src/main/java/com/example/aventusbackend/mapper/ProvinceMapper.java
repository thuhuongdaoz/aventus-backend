package com.example.aventusbackend.mapper;

import com.example.aventusbackend.dto.request.EmployerRequest;
import com.example.aventusbackend.dto.response.EmployerResponse;
import com.example.aventusbackend.dto.response.ProvinceResponse;
import com.example.aventusbackend.entity.Employer;
import com.example.aventusbackend.entity.Province;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProvinceMapper {
//    Employer toEmployer(EmployerRequest request);
    ProvinceResponse toProvinceResponse(Province province);

}
