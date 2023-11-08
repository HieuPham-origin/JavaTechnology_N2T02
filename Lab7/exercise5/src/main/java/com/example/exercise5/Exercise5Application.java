package com.example.exercise5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Optional;

@SpringBootApplication
public class Exercise5Application extends SpringBootServletInitializer implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Exercise5Application.class, args);
	}
	@Autowired
	private StudentService studentServices;

	@Override
	public void run(String...args) throws Exception {
		Student student1 = new Student(1L, "HieuPham", 20, "mhieu@gmail.com",4.0);
		studentServices.createStudent(student1);
		Student student2 = new Student(2L, "HoangPhu", 20, "phu2@gmail.com",4.0);
		studentServices.createStudent(student2);

		Student student3 = new Student(3L, "TranPhu", 17, "phul3@gmail.com",6.5);
		studentServices.createStudent(student3);
		System.out.println("Add 3 Students");
		studentServices.getAllStudents().forEach(System.out::println);


		Optional<Student> updateStudent = studentServices.getStudentById(1L);
		if(updateStudent.isPresent()){
			Student student = updateStudent.get();
			student.setName("Hieu gaaa");
			studentServices.createStudent(student);
		}
		System.out.println("Update");
		studentServices.getAllStudents().forEach(System.out::println);

		System.out.println("Delete");
		studentServices.deleteStudentById(3L);
		studentServices.getAllStudents().forEach(System.out::println);
		System.out.println("Exercise 5:");
		System.out.println("Students are greater than 20 age are:");
		studentServices.findByAge(20).forEach(System.out::println);

		System.out.println("Students have ielts 4.0 score is:");
		System.out.println(studentServices.countByScore(4.0));
		System.out.println("Students have containing 'hieu' are:");
		studentServices.findByName("hieu").forEach(System.out::println);
	}
}

