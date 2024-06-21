package com.example.aventusbackend.controller;

import com.example.aventusbackend.dto.response.ProvinceResponse;
import com.example.aventusbackend.entity.Role;
import com.example.aventusbackend.service.ProvinceService;
import com.example.aventusbackend.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
//@CrossOrigin(origins = "http://localhost:3000")
public class RoleController {
    RoleService roleService;

    @GetMapping
    List<Role> getAll(){
        return roleService.getAll();
    }
}
