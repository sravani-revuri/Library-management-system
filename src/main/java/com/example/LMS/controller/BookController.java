package com.example.LMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.LMS.model.Book;
import com.example.LMS.service.BookService;
import com.example.LMS.service.BorrowService;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowService borrowService;

    @GetMapping("/")
    public String home() {
        return "index"; // loads index.html
    }

    @GetMapping("/books")
    public String viewBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books"; // loads books.html
    }

    // ✅ Added GET mapping to show the borrow form
    @GetMapping("/borrow")
    public String showBorrowForm(Model model) {
        List<Book> books = bookService.getAllBooks(); // or available books only
        model.addAttribute("books", books);
        return "borrow"; // make sure borrow.html exists in templates
    }

    @PostMapping("/borrow")
public String borrowBook(@RequestParam Long userId, @RequestParam Long bookId, Model model) {
    borrowService.borrowBook(userId, bookId);
    model.addAttribute("message", "Book borrowed successfully!");
    return "borrow-success";
}

}
