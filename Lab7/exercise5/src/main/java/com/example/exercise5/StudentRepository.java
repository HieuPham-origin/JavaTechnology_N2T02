package com.example.exercise5;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    @Query("SELECT S FROM Student S WHERE S.age >= ?1")
    List<Student> findByAge(int age);

    @Query("SELECT COUNT(S) FROM Student S WHERE S.ieltsScore = ?1")
    int countByScore(double score);

    @Query("SELECT S FROM Student S WHERE S.name like %?1%")
    List<Student> findByName(String x);
}
