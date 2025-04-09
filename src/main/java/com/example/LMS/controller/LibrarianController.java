package com.example.LMS.controller;

import com.example.LMS.model.Librarian;
import com.example.LMS.repository.LibrarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LibrarianController {

    @Autowired
    private LibrarianRepository librarianRepository;

    @GetMapping("/librarians")
    public String getAllLibrarians(Model model) {
        List<Librarian> librarians = librarianRepository.findAll();
        model.addAttribute("librarians", librarians);
        return "librarians"; // Refers to librarians.html
    }
}
