package com.fuser.educate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class EducateApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(EducateApplication.class, args);
	}


}
