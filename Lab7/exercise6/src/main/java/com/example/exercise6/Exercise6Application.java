package com.example.exercise6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class Exercise6Application extends SpringBootServletInitializer implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Exercise6Application.class, args);
	}
	@Autowired
	private CloneStudentRepository studentRepository;
	@Override
	public void run(String... args) throws Exception{
		Page<Student> studentsByAge = studentRepository.findAllByOrderByAgeDescIeltsScoreAsc(PageRequest.of(0, 10));
		System.out.println("Students sorted by age in descending order and ielts in ascending order:");
		for (Student student : studentsByAge.getContent()) {
			System.out.println(student);
		}
		Page<Student> students456 = studentRepository.findAll(PageRequest.of(1, 3));
		System.out.println("Students on page 2 (4-5-6):");
		for (Student student : students456.getContent()) {
			System.out.println(student);
		}
	}
}
