package com.example.exercise4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepo;

    public Student createStudent(Student student) {
        return studentRepo.save(student);
    }

    public List<Student> getAllStudents() {
        return (List<Student>) studentRepo.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepo.findById(id);
    }

    public Student updateStudent(Student updatedStudent) {
        return studentRepo.save(updatedStudent);
    }

    public void deleteStudentById(Long id) {
        studentRepo.deleteById(id);
    }

    public List<Student> findStudentsByAgeGreaterThanEqual(int age){
        return studentRepo.findStudentsByAgeGreaterThanEqual(age);
    }

    public int countStudentsByIeltsScore(double ieltsscore){
        return studentRepo.countByIeltsScore(ieltsscore);
    }

    public List<Student> findStudentsByNameContainingIgnoreCase(String x){
        return studentRepo.findByNameContainingIgnoreCase(x);
    }
}
