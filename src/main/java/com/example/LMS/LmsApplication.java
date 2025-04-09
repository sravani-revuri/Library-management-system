package com.example.LMS;

import com.example.LMS.model.Librarian;
import com.example.LMS.repository.LibrarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class LmsApplication implements CommandLineRunner {

    @Autowired
    private LibrarianRepository librarianRepository;

    public static void main(String[] args) {
        SpringApplication.run(LmsApplication.class, args);
    }

    @Override
    public void run(String... args) {
        List<Librarian> librarians = librarianRepository.findAll();
        librarians.forEach(System.out::println);
    }
}
