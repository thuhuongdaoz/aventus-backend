package com.example.aventusbackend.repository;


import com.example.aventusbackend.entity.Role;
import com.example.aventusbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

}
