package com.example.LMS.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LMS.model.User;
import com.example.LMS.repository.UserRepository;
import com.example.LMS.factory.UserFactory;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(String name, String email, String password) {
        User user = (User) UserFactory.createUser("user", name, email, password, null);
        return userRepository.save(user);
    }

    public Optional<Long> loginUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            return Optional.of(userOptional.get().getId());
        }
        return Optional.empty();
    }

    public boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
