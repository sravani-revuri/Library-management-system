package com.example.LMS.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.LMS.model.User;
import com.example.LMS.service.IUserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    @Qualifier("userServiceProxy")
    private IUserService userService;

    @GetMapping("/")
    public String showLandingPage() {
        return "landing";
    }

    @GetMapping("/home")
    public String showHomePage(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null) {
            return "redirect:/";
        }
        
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "index";
        }
        
        return "redirect:/";
    }
}
