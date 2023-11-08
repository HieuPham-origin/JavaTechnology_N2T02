package com.example.exercise2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Optional;

@SpringBootApplication
public class Exercise2Application extends SpringBootServletInitializer implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Exercise2Application.class, args);
	}
	@Autowired
	private StudentService studentServices;

	@Override
	public void run(String...args) throws Exception {
		Student student1 = new Student(1L, "HieuPham", 19, "hieupham@gmail.com",4.0);
		studentServices.createStudent(student1);
		Student student2 = new Student(2L, "PhuLE", 19, "phule@gmail.com",5.5);
		studentServices.createStudent(student2);

		Student student3 = new Student(3L, "PhuPhan", 20, "phuphan@gmail.com",6.5);
		studentServices.createStudent(student3);
		System.out.println("Add 3 Students");
		studentServices.getAllStudents().forEach(System.out::println);


		Optional<Student> updateStudent = studentServices.getStudentById(1L);
		if(updateStudent.isPresent()){
			Student student = updateStudent.get();
			student.setName("Hieu ngu");
			studentServices.createStudent(student);
		}
		System.out.println("Update Hieu");
		studentServices.getAllStudents().forEach(System.out::println);

		System.out.println("Delete Phu Phan");
		studentServices.deleteStudentById(3L);
		studentServices.getAllStudents().forEach(System.out::println);
	}
}
