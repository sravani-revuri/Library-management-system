package com.example.LMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LMS.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
