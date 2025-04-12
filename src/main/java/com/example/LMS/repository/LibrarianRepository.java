package com.example.LMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LMS.model.Librarian;

public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {
    Librarian findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
}
