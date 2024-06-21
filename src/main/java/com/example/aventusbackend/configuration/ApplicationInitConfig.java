package com.example.aventusbackend.configuration;


import com.example.aventusbackend.entity.User;
import com.example.aventusbackend.enums.Role;
import com.example.aventusbackend.repository.RoleRepository;
import com.example.aventusbackend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            if (userRepository.findByEmail("admin@gmail.com").isEmpty()){
                var role = roleRepository.findById(Role.ADMIN.name()).orElseThrow(
                        () -> new RuntimeException());
//                User user = User.builder()
//                        .email("admin@gmail.com")
//                        .name("admin")
//                        .password(passwordEncoder.encode("123456"))
//                        .role(role)
//                        .build();
                User user = new User();
                user.setEmail("admin@gmail.com");
                user.setName("admin");
                user.setPassword(passwordEncoder.encode("123456"));
                user.setRole(role);


                userRepository.save(user);
                log.info("admin user has been created with default password: 123456");
            }
        };
    }
}
