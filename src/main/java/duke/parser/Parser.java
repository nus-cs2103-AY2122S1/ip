package duke.parser;

import java.time.LocalDate;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.DukeException;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Converts the user input of an task date into a LocalDate object.
     *
     * @param eventDate The string representing the date inputted by the user.
     * @return The LocalDate object representing the task date.
     * @throws DukeException if user input is of an incorrect format.
     */
    public static LocalDate convertDate(String eventDate) throws DukeException {
        if (eventDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return LocalDate.parse(eventDate);
        } else {
            throw new DukeException("Please follow date format: yyyy-mm-dd");
        }
    }

    /**
     * Parses user input and executes the appropriate response.
     *
     * @param fullCommand The string representing the command inputted by the user.
     * @return The command based on the user input.
     * @throws DukeException If user input is invalid.
     */
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
        case "find":
            return new FindCommand(arguments);
        default:
            throw new DukeException("I'm sorry, but I don't know what \""
                    + fullCommand + "\" means :-(");

        }
    }

}
