package com.example.LMS.repository;

import com.example.LMS.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Change 'findByName' to 'findByTitle' to match the 'title' field in the Book entity
    List<Book> findByTitle(String title);
}
