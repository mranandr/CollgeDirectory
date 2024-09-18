package com.CDA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
public class CollegeApplication {
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:3000") // Allow requests from this origin
				.allowedMethods("*")
				.allowedHeaders("*");
	}

	public static void main(String[] args) {
		SpringApplication.run(CollegeApplication.class, args);

	}
}