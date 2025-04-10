package com.example.LMS.command;

import org.springframework.stereotype.Component;

@Component
public class CommandExecutor {

    public boolean run(Command command) {
        return command.execute();
    }
}
