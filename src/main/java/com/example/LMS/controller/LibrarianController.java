package com.example.LMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.LMS.model.Librarian;
import com.example.LMS.service.LibrarianService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/librarian") // Adds a common prefix for all routes
public class LibrarianController {

    @Autowired
    private LibrarianService librarianService;

    // Show login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "librarian_login"; // src/main/resources/templates/librarian_login.html
    }

    // Show registration form
    @GetMapping("/register")
    public String showRegisterForm() {
        return "librarian_register"; // src/main/resources/templates/librarian_register.html
    }

    // Process registration form
    @PostMapping("/register")
    public String registerLibrarian(@RequestParam String name,
                                    @RequestParam String email,
                                    @RequestParam String password,
                                    @RequestParam String phone,
                                    Model model) {

        // Check if email already registered
        if (librarianService.librarianExists(email)) {
            model.addAttribute("error", "Email already exists!");
            return "librarian_register";
        }

        // Register librarian
        librarianService.registerLibrarian(name, email, password, phone);
        model.addAttribute("message", "Librarian registered successfully. Please login.");
        return "librarian_login";
    }

    // Process login form
    @PostMapping("/login")
    public String loginLibrarian(@RequestParam String email,
                                 @RequestParam String password,
                                 HttpSession session,
                                 Model model) {

        Librarian librarian = librarianService.loginLibrarian(email, password);

        if (librarian == null) {
            model.addAttribute("error", "Invalid email or password.");
            return "librarian_login";
        }

        session.setAttribute("loggedInLibrarian", librarian);
        return "redirect:/librarian/dashboard";
    }

    // Librarian dashboard page
    @GetMapping("/dashboard")
    public String librarianDashboard(Model model, HttpSession session) {
        Librarian librarian = (Librarian) session.getAttribute("loggedInLibrarian");
        if (librarian == null) {
            return "redirect:/librarian/login";
        }
        model.addAttribute("librarian", librarian);
        return "librarian_dashboard"; // Create this HTML page
    }

    // Optional: List of all librarians (admin use)
    @GetMapping("/all")
    public String getAllLibrarians(Model model) {
        List<Librarian> librarians = librarianService.getAllLibrarians();
        model.addAttribute("librarians", librarians);
        return "librarians"; // src/main/resources/templates/librarians.html
    }
}
