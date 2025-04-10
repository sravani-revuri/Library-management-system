package com.example.LMS.controller;

import com.example.LMS.model.ReturnBook;
import com.example.LMS.service.ReturnBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReturnBookController {

    @Autowired
    private ReturnBookService returnBookService;

    @GetMapping("/return-book")
    public String showReturnPage() {
        return "return-book";
    }

    @PostMapping("/return-book")
    public String returnBook(@RequestParam Long borrowId, Model model) {
        ReturnBook returnedBook = returnBookService.returnBook(borrowId);

        if (returnedBook != null) {
            model.addAttribute("returnedBook", returnedBook);
            return "return-success"; // Redirect to new success page
        } else {
            model.addAttribute("message", "❌ Error: Borrow ID not found.");
            return "return-book";
        }
    }
}
