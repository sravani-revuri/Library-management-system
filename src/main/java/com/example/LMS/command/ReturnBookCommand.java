package com.example.LMS.command;

import com.example.LMS.model.Borrow;
import com.example.LMS.model.ReturnBook;
import com.example.LMS.repository.BorrowRepository;
import com.example.LMS.repository.ReturnBookRepository;

import java.time.LocalDate;

public class ReturnBookCommand implements Command {

    private final Long borrowId;
    private final BorrowRepository borrowRepository;
    private final ReturnBookRepository returnBookRepository;
    private ReturnBook returnBook;

    public ReturnBookCommand(Long borrowId, BorrowRepository borrowRepository, ReturnBookRepository returnBookRepository) {
        this.borrowId = borrowId;
        this.borrowRepository = borrowRepository;
        this.returnBookRepository = returnBookRepository;
    }

    @Override
    public boolean execute() {
        Borrow borrow = borrowRepository.findById(borrowId).orElse(null);
        if (borrow == null) return false;

        Long userId = borrow.getUser().getId();
        Long bookId = borrow.getBook().getId();

        returnBook = new ReturnBook(borrowId, userId, bookId, LocalDate.now());
        returnBookRepository.save(returnBook);
        borrowRepository.deleteById(borrowId);
        return true;
    }

    public ReturnBook getReturnBook() {
        return returnBook;
    }
}
