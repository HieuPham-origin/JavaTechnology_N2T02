package com.example.exercise6;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CloneStudentRepository extends PagingAndSortingRepository<Student, Integer> {
    public Page<Student> findAllByOrderByAgeDescIeltsScoreAsc(Pageable pageable);

}
