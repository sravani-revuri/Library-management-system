package com.example.LMS.controller;

import java.util.List;

import com.example.LMS.model.Book;
import com.example.LMS.model.Borrow;
import com.example.LMS.service.BookService;
import com.example.LMS.service.BorrowService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowService borrowService;

    @GetMapping("/books")
public String showAllBooks(Model model) {
    List<Book> books = bookService.getAllBooks();
    model.addAttribute("books", books);
    return "books"; // books.html
}


    @PostMapping("/borrow")
public String borrowBook(@RequestParam("bookId") Long bookId,
                         HttpSession session,
                         RedirectAttributes redirectAttributes) {
    Long userId = (Long) session.getAttribute("loggedInUserId");

    if (userId == null) {
        redirectAttributes.addFlashAttribute("error", "You must be logged in to borrow a book.");
        return "redirect:/user/login";
    }

    try {
        borrowService.borrowBook(userId, bookId);
        redirectAttributes.addFlashAttribute("message", "Book borrowed successfully!");
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("error", "Could not borrow book.");
    }

    return "redirect:/books";
}

    
    @GetMapping("/my-borrows")
    public String viewMyBorrowedBooks(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("loggedInUserId");

        if (userId == null) {
            model.addAttribute("error", "User not logged in.");
            return "user_login"; // Redirect to login if not logged in
        }

        List<Borrow> myBorrows = borrowService.getBorrowsByUserId(userId);
        model.addAttribute("borrows", myBorrows);
        return "user_borrowed_books"; // View that displays this info
    }
}
