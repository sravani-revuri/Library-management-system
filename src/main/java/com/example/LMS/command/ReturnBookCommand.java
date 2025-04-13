package com.example.LMS.command;

import com.example.LMS.model.Borrow;
import com.example.LMS.model.Fine;
import com.example.LMS.model.ReturnBook;
import com.example.LMS.repository.BorrowRepository;
import com.example.LMS.repository.FineRepository;
import com.example.LMS.repository.ReturnBookRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReturnBookCommand implements Command {

    private final Long borrowId;
    private final BorrowRepository borrowRepository;
    private final ReturnBookRepository returnBookRepository;
    private final FineRepository fineRepository;
    private ReturnBook returnBook;
    private Fine fine;

    public ReturnBookCommand(Long borrowId, BorrowRepository borrowRepository,
                             ReturnBookRepository returnBookRepository, FineRepository fineRepository) {
        this.borrowId = borrowId;
        this.borrowRepository = borrowRepository;
        this.returnBookRepository = returnBookRepository;
        this.fineRepository = fineRepository;
    }

    @Override
    public boolean execute() {
        Borrow borrow = borrowRepository.findById(borrowId).orElse(null);
        if (borrow == null) return false;

        Long userId = borrow.getUser().getId();
        Long bookId = borrow.getBook().getId();
        LocalDate borrowDate = borrow.getBorrowDate();
        LocalDate returnDate = LocalDate.now();
        LocalDate dueDate = borrowDate.plusDays(14);

        int fineAmount = 0;
        if (returnDate.isAfter(dueDate)) {
            fineAmount = (int) ChronoUnit.DAYS.between(dueDate, returnDate) * 10;
        }

        returnBook = new ReturnBook(borrowId, userId, bookId, returnDate);
        returnBookRepository.save(returnBook);
        borrowRepository.deleteById(borrowId);

        if (fineAmount > 0) {
            fine = new Fine();
            fine.setAmount(fineAmount);
            fine.setReturnBookId(returnBook.getId());
            fineRepository.save(fine);
        }

        return true;
    }

    public ReturnBook getReturnBook() {
        return returnBook;
    }

    public Fine getFine() {
        return fine;
    }
}
