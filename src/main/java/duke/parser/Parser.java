package duke.parser;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.DukeException;

import java.time.LocalDate;

public class Parser {

    public static LocalDate convertDate(String eventDate) throws DukeException {
        if (eventDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return LocalDate.parse(eventDate);
        } else {
            throw new DukeException("Please follow date format: yyyy-mm-dd");
        }
    }

    public static Command parse(String fullCommand) throws DukeException {
        String command;
        String arguments;

        if (fullCommand.isEmpty()) {
            throw new DukeException("Please enter a command");
        }

        if (!fullCommand.contains(" ")) {
            command = fullCommand;
            arguments = "";
        } else {
            command = fullCommand.substring(0, fullCommand.indexOf(" "));
            arguments = fullCommand.substring(fullCommand.indexOf(" ") + 1).trim();
        }

        switch (command) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ByeCommand();
        case "delete":
            return new DeleteCommand(arguments);
        case "done":
            return new DoneCommand(arguments);
        case "event":
            return new EventCommand(arguments);
        case "deadline":
            return new DeadlineCommand(arguments);
        case "todo":
            return new TodoCommand(arguments);
        default:
            throw new DukeException("I'm sorry, but I don't know what \""
                    + fullCommand + "\" means :-(");
        }
    }

}
