package com.example.LMS.factory;

import com.example.LMS.model.Librarian;
import com.example.LMS.model.User;

public class UserFactory {

    public static Object createUser(String userType, String name, String email, String password, String phone) {
        switch (userType.toLowerCase()) {
            case "librarian":
                return new Librarian(name, email, password, phone);
            case "user":
                return new User.UserBuilder()
                        .setName(name)
                        .setEmail(email)
                        .setPassword(password)
                        .build();
            default:
                throw new IllegalArgumentException("Unknown user type: " + userType);
        }
    }
}
