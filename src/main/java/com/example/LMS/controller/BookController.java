package com.example.LMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.LMS.model.Book;
import com.example.LMS.repository.BookRepository;
import com.example.LMS.service.BorrowService;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowService borrowService;

    // View all books
    @GetMapping("/books")
    public String viewBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    // Borrow form
    @GetMapping("/borrow")
    public String showBorrowForm(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "borrow";
    }

    @PostMapping("/borrow")
    public String borrowBook(@RequestParam Long userId,
                             @RequestParam Long bookId,
                             Model model) {
        borrowService.borrowBook(userId, bookId);
        model.addAttribute("message", "Book borrowed successfully!");
        return "borrow-success"; // ✅ success view with redirect
    }

    @GetMapping("/index")
    public String showHomePage(Model model) {
        model.addAttribute("message", "Welcome back!");
        return "index";
    }
}
