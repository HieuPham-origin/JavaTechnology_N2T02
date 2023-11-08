package com.example.exercise1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Exercise1Application
{

	public static void main(String[] args) {
		SpringApplication.run(Exercise1Application.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("This is Beannn");
		};
	}
}
