package com.example.aventusbackend.service;

import com.example.aventusbackend.dto.response.ProvinceResponse;
import com.example.aventusbackend.entity.Role;
import com.example.aventusbackend.mapper.ProvinceMapper;
import com.example.aventusbackend.repository.ProvinceRepository;
import com.example.aventusbackend.repository.RoleRepository;
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
public class RoleService {
    RoleRepository roleRepository;

    public List<Role> getAll(){
        return roleRepository.findAll();
    }

}
