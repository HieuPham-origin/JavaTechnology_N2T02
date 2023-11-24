package com.example.lab9.controller;

import com.example.lab9.dto.UserDto;
import com.example.lab9.model.User;
import com.example.lab9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {
        // Validate userDto and perform necessary checks

        // Create a new User instance
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        // Save the user in the database
        userService.register(user);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        // Validate userDto and perform necessary checks

        // Authenticate the user
        boolean authenticated = userService.authenticate(userDto.getUsername(), userDto.getPassword());

        if (authenticated) {
            return ResponseEntity.ok("User logged in successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
