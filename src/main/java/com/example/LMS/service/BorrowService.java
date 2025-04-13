package com.example.LMS.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LMS.model.Book;
import com.example.LMS.model.Borrow;
import com.example.LMS.model.User;
import com.example.LMS.repository.BookRepository;
import com.example.LMS.repository.BorrowRepository;
import com.example.LMS.repository.UserRepository;

@Service
public class BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    // Borrow a book by ID
    public void borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();

        Borrow borrow = new Borrow();
        borrow.setUser(user);
        borrow.setBook(book);
        borrow.setBorrowDate(LocalDate.now());
        borrow.setReturnDate(LocalDate.now().plusDays(14)); // default 2 weeks

        borrowRepository.save(borrow);
    }

    public List<Borrow> getAllBorrowedBooks() {
        return borrowRepository.findAll();
    }

    public List<Borrow> getBorrowsByUserId(Long userId) {
        return borrowRepository.findByUserId(userId);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public boolean issueBookByTitle(Long userId, String bookTitle) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return false;
    
        List<Book> matchingBooks = bookRepository.findByTitle(bookTitle);
        if (matchingBooks.isEmpty()) return false;
    
        Book book = matchingBooks.get(0);
    
        Borrow borrow = new Borrow();
        borrow.setUser(user);
        borrow.setBook(book);
        borrow.setBorrowDate(LocalDate.now());
        borrow.setReturnDate(LocalDate.now().plusDays(14));
    
        borrowRepository.save(borrow);
        return true;
    }
    
}
