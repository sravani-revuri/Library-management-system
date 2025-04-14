package com.example.LMS.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.LMS.model.User;
import com.example.LMS.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showLandingPage(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null) {
            return "landing";
        }
        
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "index";
        }
        
        return "landing";
    }
}
