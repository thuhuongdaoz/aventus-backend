package com.example.aventusbackend.mapper;

import com.example.aventusbackend.dto.request.CandidateUpdateRequest;
import com.example.aventusbackend.dto.request.UserRequest;
import com.example.aventusbackend.dto.response.UserResponse;
import com.example.aventusbackend.entity.Candidate;
import com.example.aventusbackend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest request);
    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserRequest request);

}
