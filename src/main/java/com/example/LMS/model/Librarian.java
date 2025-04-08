package com.example.LMS.model;

import jakarta.persistence.*;

@Entity
@Table(name = "librarians")
public class Librarian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int librarianID;

    private String name;
    private String email;
    private String phone;

    // Getters and setters

    public int getLibrarianID() {
        return librarianID;
    }

    public void setLibrarianID(int librarianID) {
        this.librarianID = librarianID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "librarianID=" + librarianID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
