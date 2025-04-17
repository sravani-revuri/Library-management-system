package com.example.LMS.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.LMS.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceProxy implements IUserService {
    
    private static final Logger logger = LoggerFactory.getLogger(UserServiceProxy.class);
    
    @Autowired
    private RealUserService realUserService;

    @Override
    public User registerUser(String name, String email, String password) {
        logger.info("Attempting to register user with email: {}", email);
        User user = realUserService.registerUser(name, email, password);
        logger.info("Successfully registered user with ID: {}", user.getId());
        return user;
    }

    @Override
    public Optional<Long> loginUser(String email, String password) {
        logger.info("Login attempt for user: {}", email);
        Optional<Long> result = realUserService.loginUser(email, password);
        if (result.isPresent()) {
            logger.info("Successful login for user: {}", email);
        } else {
            logger.warn("Failed login attempt for user: {}", email);
        }
        return result;
    }

    @Override
    public boolean userExists(String email) {
        logger.debug("Checking if user exists: {}", email);
        return realUserService.userExists(email);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        logger.debug("Fetching user by ID: {}", id);
        return realUserService.getUserById(id);
    }
}