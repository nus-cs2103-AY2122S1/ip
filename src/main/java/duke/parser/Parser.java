package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Parses the user input.
 */
public class Parser {

    /**
     * Creates todo when given userInput.
     * @param userInput the input by the user.
     * @return the todo inputted by the user.
     * @throws DukeException if no description is inputted.
     */
    private static Todo createTodo(String userInput) throws DukeException {
        String description = userInput.substring(4).trim();
        if (description.length() == 0) {
            throw new DukeException(":( OOPS!!! The description cannot be empty.");
        }
        return new Todo(description);
    }

    /**
     * Creates deadline when given userInput.
     * @param userInput the input by the user.
     * @return the deadline inputted by the user.
     * @throws DukeException if no date and time or description of deadline is inputted.
     */
    private static Deadline createDeadline(String userInput) throws DukeException {
        String[] splitByBy = userInput.split(" /by ");
        if (splitByBy.length == 1) {
            throw new DukeException(":( OOPS!!! The day of a deadline cannot be empty.");
        }
        String description = splitByBy[0].substring(8).trim();
        if (description.length() == 0) {
            throw new DukeException(":( OOPS!!! The description cannot be empty.");
        }
        String by = splitByBy[1];
        String[] splitDateTime = by.split(" ");
        LocalDate date = LocalDate.parse(splitDateTime[0], DateTimeFormatter.ofPattern("d/M/yyyy"));
        LocalTime time = null;
        if (splitDateTime.length == 2) {
            time = LocalTime.parse(splitDateTime[1], DateTimeFormatter.ofPattern("HHmm"));
        }
        return new Deadline(description, date, time);
    }

    /**
     * Creates event when given userInput.
     * @param userInput the input by the user.
     * @return the event inputted by the user.
     * @throws DukeException if no date and time or description of event is inputted.
     */
    private static Event createEvent(String userInput) throws DukeException {
        String[] splitByAt = userInput.split(" /at ");
        if (splitByAt.length == 1) {
            throw new DukeException(":( OOPS!!! The day of a deadline cannot be empty.");
        }
        String description = splitByAt[0].substring(5).trim();
        if (description.length() == 0) {
            throw new DukeException(":( OOPS!!! The description cannot be empty.");
        }
        String dayTime = splitByAt[1];
        String[] splitDateTime = dayTime.split(" ");
        LocalDate date = LocalDate.parse(splitDateTime[0], DateTimeFormatter.ofPattern("d/M/yyyy"));
        LocalTime time = null;
        if (splitDateTime.length == 2) {
            time = LocalTime.parse(splitDateTime[1], DateTimeFormatter.ofPattern("HHmm"));
        }
        return new Event(description, date, time);
    }

    /**
     * Parses the string for command and returns the command.
     * @param fullCommand The string representing the command inputted by the user.
     * @return A command that can be executed to perform action specified by the user.
     * @throws DukeException if the command is not recognised.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] splitInput = fullCommand.split(" ");
        switch (splitInput[0]) {
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(Integer.parseInt(splitInput[1]));
        case "todo":
            return new AddCommand(createTodo(fullCommand));
        case "deadline":
            return new AddCommand(createDeadline(fullCommand));
        case "event":
            return new AddCommand(createEvent(fullCommand));
        case "delete":
            return new DeleteCommand(Integer.parseInt(splitInput[1]));
        case "find":
            return new FindCommand(fullCommand.substring(4).trim());
        case "bye":
            return new ExitCommand();
        default:
            throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
