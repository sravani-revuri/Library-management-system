package com.example.LMS.service;

import java.util.Optional;

import com.example.LMS.model.User;

public interface IUserService {
    User registerUser(String name, String email, String password);
    Optional<Long> loginUser(String email, String password);
    boolean userExists(String email);
    Optional<User> getUserById(Long id);
}