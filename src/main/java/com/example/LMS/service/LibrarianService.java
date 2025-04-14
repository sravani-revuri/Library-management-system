package com.example.LMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LMS.model.Librarian;
import com.example.LMS.repository.LibrarianRepository;
import com.example.LMS.util.UserFactory;

@Service
public class LibrarianService {

    @Autowired
    private LibrarianRepository librarianRepository;

    public boolean librarianExists(String email) {
        return librarianRepository.existsByEmail(email);
    }

    public void registerLibrarian(String name, String email, String password, String phone) {
        Librarian librarian = (Librarian) UserFactory.createUser("librarian", name, email, password, phone);
        librarianRepository.save(librarian);
    }

    public Librarian loginLibrarian(String email, String password) {
        return librarianRepository.findByEmailAndPassword(email, password);
    }

    public List<Librarian> getAllLibrarians() {
        return librarianRepository.findAll();
    }
}
