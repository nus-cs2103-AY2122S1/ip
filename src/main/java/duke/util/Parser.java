package duke.util;

import duke.command.*;
import duke.exception.InvalidInputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class Parser {
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
            default:
                throw new InvalidInputException("This is an unknown duke.command.");
        }
    }

    public static LocalDateTime parseDate(String deadline) throws InvalidInputException {
        try {
            return LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("We don't understand your date format.");
        }
    }
}
