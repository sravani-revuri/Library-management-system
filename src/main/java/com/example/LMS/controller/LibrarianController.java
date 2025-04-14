package com.example.LMS.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.LMS.model.Book;
import com.example.LMS.model.Librarian;
import com.example.LMS.repository.BookRepository;
import com.example.LMS.service.LibrarianService;
import com.example.LMS.util.BookFactory;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/librarian") // All routes prefixed with /librarian
public class LibrarianController {

    @Autowired
    private LibrarianService librarianService;

    @Autowired
    private BookRepository bookRepository;

    // Show login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "librarian_login";
    }

    // Show registration form
    @GetMapping("/register")
    public String showRegisterForm() {
        return "librarian_register";
    }

    // Process registration
    @PostMapping("/register")
    public String registerLibrarian(@RequestParam String name,
                                    @RequestParam String email,
                                    @RequestParam String password,
                                    @RequestParam String phone,
                                    Model model) {

        if (librarianService.librarianExists(email)) {
            model.addAttribute("error", "Email already exists!");
            return "librarian_register";
        }

        librarianService.registerLibrarian(name, email, password, phone);
        model.addAttribute("message", "Librarian registered successfully. Please login.");
        return "librarian_login";
    }

    // Process login
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
        return "redirect:/librarian/dashboard"; // Make sure this goes to librarian's dashboard
    }

    // Dashboard
    @GetMapping("/dashboard")
    public String librarianDashboard(Model model, HttpSession session) {
        Librarian librarian = (Librarian) session.getAttribute("loggedInLibrarian");
        if (librarian == null) {
            return "redirect:/librarian/login"; // Always redirect to librarian login if not logged in
        }
        model.addAttribute("librarian", librarian);
        return "librarian_dashboard";
    }

    // Show add book form
    @GetMapping("/add-book")
    public String showAddBookForm(HttpSession session) {
        if (session.getAttribute("loggedInLibrarian") == null) {
            return "redirect:/librarian/login"; // Ensure librarian is logged in to access this page
        }
        return "add-book";
    }

    // Process add book
    @PostMapping("/add-book")
    public String addBook(@RequestParam String title,
                          @RequestParam(required = false) String author,
                          Model model,
                          HttpSession session) {

        if (session.getAttribute("loggedInLibrarian") == null) {
            return "redirect:/librarian/login"; // Redirect to login if librarian is not logged in
        }

        Book newBook = BookFactory.createNewBook(title, author); // Singleton pattern
        bookRepository.save(newBook);

        model.addAttribute("message", "Book added successfully!");
        return "redirect:/librarian/dashboard"; // Redirect to librarian dashboard
    }
    // Logout librarian
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Clear all session attributes
        return "redirect:/librarian/login"; // Redirect to librarian login page
    }

}

