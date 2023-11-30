package com.example.lab9.repository;

import com.example.lab9.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByUsername(String username);

}
