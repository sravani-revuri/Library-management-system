package com.example.LMS.controller;

import com.example.LMS.model.Book;
import com.example.LMS.repository.BookRepository;
import com.example.LMS.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.LMS.util.BookFactory;


import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowService borrowService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/books")
    public String viewBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "books";
    }

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
        return "borrow-success";
    }

    // ✅ Add Book Form (GET)
    @GetMapping("/add-book")
    public String showAddBookForm() {
        return "add-book";
    }

    // ✅ Handle Add Book Submission (POST)
    @PostMapping("/add-book")
public String addBook(@RequestParam String title,
                      @RequestParam(required = false) String author,
                      Model model) {

    Book newBook = BookFactory.createNewBook(title, author); // ✅ Using singleton
    bookRepository.save(newBook);

    model.addAttribute("title", title);
    return "book-added";
}



}
