package com.example.LMS.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "return_book")
public class ReturnBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary Key (auto-increment)

    private Long borrowId;
    private Long userId;
    private Long bookId;
    private LocalDate returnDate;

    public ReturnBook() {}

    public ReturnBook(Long borrowId, Long userId, Long bookId, LocalDate returnDate) {
        this.borrowId = borrowId;
        this.userId = userId;
        this.bookId = bookId;
        this.returnDate = returnDate;
    }

    public Long getId() {
        return id;
    }

    public Long getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
