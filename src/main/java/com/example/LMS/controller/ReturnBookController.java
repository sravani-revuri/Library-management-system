package com.example.LMS.controller;

import com.example.LMS.model.Borrow;
import com.example.LMS.model.ReturnBook;
import com.example.LMS.service.BorrowService;
import com.example.LMS.service.ReturnBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/return-book")
    public String returnBook(@RequestParam Long borrowId, Model model) {
        ReturnBook returnedBook = returnBookService.returnBook(borrowId);

        if (returnedBook != null) {
            model.addAttribute("returnedBook", returnedBook);
            return "return-success";
        } else {
            model.addAttribute("message", "❌ Error: Borrow ID not found.");
            return "redirect:/return-book";
        }
    }
}