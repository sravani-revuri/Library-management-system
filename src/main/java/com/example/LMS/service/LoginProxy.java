package com.example.LMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginProxy implements LoginService {

    @Autowired
    private RealLoginService realLoginService;

    @Override
    public boolean login(String email, String password) {
        System.out.println("🔒 Login attempt for: " + email);

        // Add security features here if needed (e.g., logging, rate-limiting)

        boolean result = realLoginService.login(email, password);

        System.out.println(result ? "✅ Login successful" : "❌ Login failed");

        return result;
    }
}
