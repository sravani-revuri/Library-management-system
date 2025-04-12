package com.example.LMS.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LMS.model.User;
import com.example.LMS.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(String name, String email, String password) {
        User user = new User.UserBuilder()
                .setName(name)
                .setEmail(email)
                .setPassword(password)
                .build();

        return userRepository.save(user);
    }

    public boolean loginUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.isPresent() && userOptional.get().getPassword().equals(password);
    }

    public boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
