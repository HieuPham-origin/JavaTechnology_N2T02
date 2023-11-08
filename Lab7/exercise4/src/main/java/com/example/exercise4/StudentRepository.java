package com.example.exercise4;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findStudentsByAgeGreaterThanEqual(int age);
    int countByIeltsScore(double ietlsscore);
    List<Student> findByNameContainingIgnoreCase(String x);
}
