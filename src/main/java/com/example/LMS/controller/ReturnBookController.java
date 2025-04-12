package com.example.LMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.LMS.model.Borrow;
import com.example.LMS.model.ReturnBook;
import com.example.LMS.service.BorrowService;
import com.example.LMS.service.ReturnBookService;

@Controller
public class ReturnBookController {

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private ReturnBookService returnBookService;

    @GetMapping("/return-book")
    public String showBorrowedBooks(Model model) {
        List<Borrow> borrowedBooks = borrowService.getAllBorrowedBooks();
        model.addAttribute("borrowedBooks", borrowedBooks);
        return "return-book";
    }

    // Process the return book action
    @PostMapping("/return-book")
    public String returnBook(@RequestParam Long borrowId, Model model) {
        // Return the book by borrowId
        ReturnBook returnedBook = returnBookService.returnBook(borrowId);

        if (returnedBook != null) {
            model.addAttribute("message", "Book returned successfully!");
        } else {
            model.addAttribute("message", "❌ Error: Borrow ID not found.");
        }

        // Redirect to the librarian dashboard, whether the operation is successful or not
        return "redirect:/librarian/dashboard";  // Redirecting to the dashboard
    }
}
