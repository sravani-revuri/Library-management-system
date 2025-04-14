package com.example.LMS.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.LMS.model.User;
import com.example.LMS.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class AuthController {

    @Autowired
    private UserService userService;

    // Landing page (root)
    @GetMapping("/")
    public String showLandingPage() {
        return "landing"; // This will be accessed via "/user/"
    }

    // Show user login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // Handle user login
    @PostMapping("/login")
    public String handleLogin(@RequestParam String email,
                              @RequestParam String password,
                              HttpSession session,
                              Model model) {
    
        Optional<Long> userIdOptional = userService.loginUser(email, password);
        if (userIdOptional.isPresent()) {
            session.setAttribute("loggedInUserId", userIdOptional.get()); // Store userId in session
            return "redirect:/user/index";
        } else {
            model.addAttribute("error", "Invalid email or password.");
            return "login";
        }
    }
    

    // Show user registration page
    @GetMapping("/register")
    public String showRegisterPage() {
        return "user-register"; // ✅ Must match the HTML file name
    }

    // Handle user registration
    @PostMapping("/register")
    public String handleRegister(@RequestParam String name,
                                 @RequestParam String email,
                                 @RequestParam String password,
                                 Model model) {

        if (userService.userExists(email)) {
            model.addAttribute("error", "Email already registered.");
            return "user-register"; // ✅ Must match the HTML file name
        }

        userService.registerUser(name, email, password);
        model.addAttribute("message", "Registration successful! Please login.");
        return "login";
    }

    // User dashboard
    @GetMapping("/index")
    public String showUserDashboard(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null) {
            return "redirect:/user/login";
        }
        
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
        }
        
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Clear all session attributes
        return "redirect:/user/login"; // Redirect to login page
    }
}
