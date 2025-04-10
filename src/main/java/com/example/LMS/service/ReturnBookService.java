package com.example.LMS.service;

import com.example.LMS.command.CommandExecutor;
import com.example.LMS.command.ReturnBookCommand;
import com.example.LMS.model.ReturnBook;
import com.example.LMS.repository.BorrowRepository;
import com.example.LMS.repository.ReturnBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReturnBookService {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private ReturnBookRepository returnBookRepository;

    @Autowired
    private CommandExecutor commandExecutor;

    public ReturnBook returnBook(Long borrowId) {
        ReturnBookCommand command = new ReturnBookCommand(
                borrowId,
                borrowRepository,
                returnBookRepository
        );

        boolean success = commandExecutor.run(command);
        return success ? command.getReturnBook() : null;
    }
}
