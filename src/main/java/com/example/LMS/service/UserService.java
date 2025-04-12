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

    public Optional<Long> loginUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            return Optional.of(userOptional.get().getId()); // Return userId if login is successful
        }
        return Optional.empty(); // Return an empty Optional if the credentials are incorrect
    }
    

    public boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
