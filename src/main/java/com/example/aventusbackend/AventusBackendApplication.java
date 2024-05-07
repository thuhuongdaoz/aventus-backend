package com.example.aventusbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class AventusBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AventusBackendApplication.class, args);
	}

}
