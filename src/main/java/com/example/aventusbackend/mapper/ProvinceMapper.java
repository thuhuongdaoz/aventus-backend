package com.example.aventusbackend.mapper;

import com.example.aventusbackend.dto.response.ProvinceResponse;
import com.example.aventusbackend.entity.Province;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProvinceMapper {
//    Employer toEmployer(EmployerRequest request);
    ProvinceResponse toProvinceResponse(Province province);

}
