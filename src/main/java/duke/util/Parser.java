package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.InvalidInputException;

/**
 * Utility class that contains static methods useful in parsing
 */
public final class Parser {
    private Parser() {

    }

    /**
     * Parses the string command and returns the correct command handler.
     *
     * @param fullCommand string input from the user
     * @return corresponding command handler
     * @throws InvalidInputException if command is not recognised.
     */
    public static Command parse(String[] fullCommand) throws InvalidInputException {
        String command = fullCommand[0];
        String action = fullCommand[1];
        switch (command) {
        case "todo":
            return new TodoCommand(action);
        case "deadline":
            return new DeadlineCommand(action);
        case "event":
            return new EventCommand(action);
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(action);
        case "bye":
            return new ByeCommand();
        case "delete":
            return new DeleteCommand(action);
        case "find":
            return new FindCommand(action);
        default:
            throw new InvalidInputException("This is an unknown command.");
        }
    }

    /**
     * Parses the string input into a LocalDateTime
     *
     * @param deadline String representation of the the date-time
     * @return LocalDateTime representation of the date-time
     * @throws InvalidInputException if input string cannot be parsed into a LocalDateTime
     */
    public static LocalDateTime parseDate(String deadline) throws InvalidInputException {
        try {
            return LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("We don't understand your date format.");
        }
    }
}
