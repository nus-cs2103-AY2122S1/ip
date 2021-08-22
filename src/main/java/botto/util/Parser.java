package botto.util;

import botto.DukeException;
import botto.command.*;

public class Parser {
    private final static String[] commands = {"list", "done", "todo", "deadline", "event", "delete", "bye", "find"};


    public static Command parse(String fullCommand) throws DukeException {
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
        case "find":
            return new FindCommand(fullCommand);
        }

        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }


    private static String findCommand(String fullCommand) {
        for(String x: commands) {
            if(fullCommand.startsWith(x)) {
                return x;
            }
        }
        return "";
    }

}
