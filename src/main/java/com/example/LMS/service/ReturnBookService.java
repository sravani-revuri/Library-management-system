package com.example.LMS.service;

import com.example.LMS.command.CommandExecutor;
import com.example.LMS.command.ReturnBookCommand;
import com.example.LMS.model.Fine;
import com.example.LMS.model.ReturnBook;
import com.example.LMS.repository.BorrowRepository;
import com.example.LMS.repository.FineRepository;
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
    private FineRepository fineRepository;

    @Autowired
    private CommandExecutor commandExecutor;

    public Integer returnBook(Long borrowId, StringBuilder fineMessage) {
        ReturnBookCommand command = new ReturnBookCommand(
                borrowId,
                borrowRepository,
                returnBookRepository,
                fineRepository
        );

        boolean success = commandExecutor.run(command);
        if (!success) return null;

        Fine fine = command.getFine();
        if (fine != null) {
            fineMessage.append("Fine: ₹").append(fine.getAmount());
            return fine.getAmount();
        } else {
            fineMessage.append("No fine. ✅");
            return 0;
        }
    }
}
