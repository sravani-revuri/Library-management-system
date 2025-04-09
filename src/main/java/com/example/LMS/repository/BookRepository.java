package com.example.LMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LMS.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
