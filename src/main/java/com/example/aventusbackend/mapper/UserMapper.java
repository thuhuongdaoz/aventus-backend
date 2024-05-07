package com.example.aventusbackend.mapper;

import com.example.aventusbackend.dto.request.CandidateRequest;
import com.example.aventusbackend.dto.request.UserRequest;
import com.example.aventusbackend.dto.response.CandidateResponse;
import com.example.aventusbackend.entity.Candidate;
import com.example.aventusbackend.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest request);

}
