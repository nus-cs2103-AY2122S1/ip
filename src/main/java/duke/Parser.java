package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

// Duke Commands
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
// Duke Exceptions
import duke.exception.DukeException;
// Duke Tasks
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Parses string inputs from user.
 */
public class Parser {
    private static final int COMMAND_EVENT_LENGTH = 5;
    private static final int COMMAND_DEADLINE_LENGTH = 8;
    private static final int COMMAND_TODO_LENGTH = 4;

    /**
     * Parses string inputs from user into a Command.
     *
     * @param input Input recieved from user.
     * @return Command corresponding to the input recieved.
     * @throws DukeException Invalid inputs recieved.
     */
    protected static Command parse(String input) throws DukeException, DateTimeParseException {
        assert input != null;

        String[] inputArray = input.split(" ");
        int selectedTask;

        switch (inputArray[0]) {
        case "help":
            return new HelpCommand();
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            try {
                if (input.contains("|")) {
                    throw new DukeException("character | is not allowed as input");
                }
                if (inputArray.length < 2) {
                    throw new DukeException("A valid index must be provided");
                }

                selectedTask = Integer.parseInt(inputArray[1]) - 1;
                return new DoneCommand(selectedTask);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid input for done, index must be an integer.");
            }
        case "delete":
            try {
                if (input.contains("|")) {
                    throw new DukeException("character | is not allowed as input");
                }
                if (inputArray.length < 2) {
                    throw new DukeException("A valid index must be provided");
                }

                selectedTask = Integer.parseInt(inputArray[1]) - 1;
                return new DeleteCommand(selectedTask);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid input for delete, index must be an integer.");
            }
        case "find":
            return new FindCommand(input.substring(5));
        case "event":
            return parseEvent(input);
        case "deadline":
            return parseDeadline(input);
        case "todo":
            if (input.contains("|")) {
                throw new DukeException("character | is not allowed as input");
            }
            return parseTodo(input);
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses an event string.
     *
     * @param input Input to be parsed.
     * @return Event representing input.
     */
    private static Command parseEvent(String input) throws DukeException, DateTimeParseException {
        String[] params;
        params = input.split("/at");

        if (params.length < 2) {
            throw new DukeException("Invalid input: missing /at date");
        }

        if (params[0].trim().length() < (COMMAND_EVENT_LENGTH)) {
            throw new DukeException("Invalid input: description must not be empty");
        }

        params[0] = params[0].substring(COMMAND_EVENT_LENGTH,
                params[0].length() - 1).trim();
        params[1] = params[1].trim();
        LocalDate date = LocalDate.parse(params[1]);

        return new AddCommand(new Event(params[0], date));
    }

    /**
     * Parses a deadline string.
     *
     * @param input Input to be parsed.
     * @return Deadline representing input.
     */
    private static Command parseDeadline(String input) throws DukeException, DateTimeParseException {
        String[] params;
        params = input.split("/by");

        if (params.length < 2) {
            throw new DukeException("Invalid input: missing /by date");
        }

        if (params[0].trim().length() < COMMAND_DEADLINE_LENGTH) {
            throw new DukeException("Invalid input: description must not be empty");
        }

        params[0] = params[0].substring(COMMAND_DEADLINE_LENGTH,
                params[0].length() - 1);
        params[1] = params[1].trim();
        LocalDate date = LocalDate.parse(params[1]);

        return new AddCommand(new Deadline(params[0], date));
    }

    /**
     * Parses a todo string.
     *
     * @param input Input to be parsed.
     * @return Todo representing input.
     * @throws DukeException If the string has an empty description.
     */
    private static Command parseTodo(String input) throws DukeException, DateTimeParseException {
        if (input.trim().length() <= COMMAND_TODO_LENGTH) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }

        String name = input.substring(COMMAND_TODO_LENGTH).trim();
        return new AddCommand(new ToDo(name));
    }
}
