package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UpdateCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Parses the user input.
 */
public class Parser {

    /**
     * Creates todo when given userInput.
     *
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

    private static LocalDate parseDateOfDeadline(String[] splitInput) {
        String by = splitInput[1];
        String[] splitDateTime = by.split(" ");
        return LocalDate.parse(splitDateTime[0], DateTimeFormatter.ofPattern("d/M/yyyy"));
    }

    private static LocalTime parseTimeOfDeadline(String[] splitInput) {
        LocalTime time = null;
        String by = splitInput[1];
        String[] splitDateTime = by.split(" ");
        if (splitDateTime.length == 2) {
            time = LocalTime.parse(splitDateTime[1], DateTimeFormatter.ofPattern("HHmm"));
        }
        return time;
    }

    /**
     * Creates deadline when given userInput.
     *
     * @param userInput the input by the user.
     * @return the deadline inputted by the user.
     * @throws DukeException if no date and time or description of deadline is inputted.
     */
    private static Deadline createDeadline(String userInput) throws DukeException {
        String[] splitInput = userInput.split(" /by ");

        if (splitInput.length == 1) {
            throw new DukeException(":( OOPS!!! The day of a deadline cannot be empty.");
        }

        String description = splitInput[0].substring(8).trim();
        if (description.length() == 0) {
            throw new DukeException(":( OOPS!!! The description cannot be empty.");
        }

        return new Deadline(description, parseDateOfDeadline(splitInput), parseTimeOfDeadline(splitInput));
    }

    private static LocalDate parseDateOfEvent(String[] splitInput) {
        String dayTime = splitInput[1];
        String[] splitDateTime = dayTime.split(" ");
        return LocalDate.parse(splitDateTime[0], DateTimeFormatter.ofPattern("d/M/yyyy"));
    }

    private static LocalTime parseTimeOfEvent(String[] splitInput) {
        String dayTime = splitInput[1];
        String[] splitDateTime = dayTime.split(" ");
        LocalTime time = null;
        if (splitDateTime.length == 2) {
            time = LocalTime.parse(splitDateTime[1], DateTimeFormatter.ofPattern("HHmm"));
        }
        return time;
    }

    /**
     * Creates event when given userInput.
     *
     * @param userInput the input by the user.
     * @return the event inputted by the user.
     * @throws DukeException if no date and time or description of event is inputted.
     */
    private static Event createEvent(String userInput) throws DukeException {
        String[] splitInput = userInput.split(" /at ");
        if (splitInput.length == 1) {
            throw new DukeException(":( OOPS!!! The day of a deadline cannot be empty.");
        }
        String description = splitInput[0].substring(5).trim();
        if (description.length() == 0) {
            throw new DukeException(":( OOPS!!! The description cannot be empty.");
        }
        return new Event(description, parseDateOfEvent(splitInput), parseTimeOfEvent(splitInput));
    }

    private static Task createTask(String fullCommand) throws DukeException {
        String[] splitInput = fullCommand.split(" ");
        switch (splitInput[0]) {
        case "todo":
            return createTodo(fullCommand);
        case "deadline":
            return createDeadline(fullCommand);
        case "event":
            return createEvent(fullCommand);
        default:
            throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses the string for command and returns the command.
     *
     * @param fullCommand the string representing the command inputted by the user.
     * @return the command that can be executed to perform action specified by the user.
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
        case "update":
            return new UpdateCommand(Integer.parseInt(splitInput[1]),
                    createTask(String.join(" ", Arrays.copyOfRange(splitInput, 2, splitInput.length))));
        case "bye":
            return new ExitCommand();
        default:
            throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
