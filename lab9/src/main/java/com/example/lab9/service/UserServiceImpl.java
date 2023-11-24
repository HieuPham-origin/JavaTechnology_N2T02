package com.example.lab9.service;

import com.example.lab9.model.User;
import com.example.lab9.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User user){
        return this.userRepository.save(user);
    }
    @Override
    public boolean authenticate(String username, String password) {
        // Retrieve the user from the database based on the username
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Perform the authentication check
            if (user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }
}
