package botto.util;

import botto.BottoException;
import botto.command.*;

public class Parser {
    private final static String[] COMMANDS = {"list", "done", "todo", "deadline", "event", "delete", "bye"};

    public static Command parse(String fullCommand) throws BottoException {
        String command = findCommand(fullCommand);

        switch (command) {
        case "list":
            return new ShowListCommand();
        case "done":
            return new MarkDoneCommand(fullCommand);
        case "todo":
            return new AddToDoCommand(fullCommand);
        case "deadline":
            return new AddDeadlineCommand(fullCommand);
        case "event":
            return new AddEventCommand(fullCommand);
        case "delete":
            return new DeleteCommand(fullCommand);
        case "bye":
            return new ExitCommand();
        }

        throw new BottoException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }


    private static String findCommand(String fullCommand) {
        for(String x: COMMANDS) {
            if(fullCommand.startsWith(x)) {
                return x;
            }
        }
        return "";
    }

}
