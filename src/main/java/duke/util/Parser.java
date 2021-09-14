package duke.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.SortCommand;
import duke.command.TodoCommand;
import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidArgumentException;
import duke.exception.InvalidDateFormatException;
import duke.exception.MissingArgumentException;
import duke.exception.UnknownCommandException;

/**
 * Represents the parser in the Duke program to parse commands by users.
 */
public class Parser {
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";
    private static final String SORT_COMMAND = "sort";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EXIT_COMMAND = "bye";

    /**
     * Parses the given raw command.
     *
     * @param fullCommand Raw command.
     * @return Parsed command.
     * @throws DukeException If there is/are missing or invalid argument(s).
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] commandComponents = fullCommand.trim().split(" ");
        String command = commandComponents[0];

        // Command arguments
        int task;
        String description;
        LocalDate by;
        String at;
        String arguments;
        String[] argumentComponents;
        String keyword;

        switch (command) {
        case Parser.DONE_COMMAND:
            // Check for missing task argument
            if (commandComponents.length == 1) {
                throw new MissingArgumentException("Done", "task");
            }

            // Extract task argument
            task = Integer.parseInt(commandComponents[1]) - 1;

            // Check for invalid task argument
            if (task < 0) {
                throw new InvalidArgumentException(task);
            }

            // If pass all checking, create and return done command obj
            assert task >= 0 : "Task should be non negative";
            return new DoneCommand(task);

        case Parser.DELETE_COMMAND:
            // Check for missing task argument
            if (commandComponents.length == 1) {
                throw new MissingArgumentException("Delete", "task");
            }

            // Extract task argument
            task = Integer.parseInt(commandComponents[1]) - 1;

            // Check for invalid task argument
            if (task < 0) {
                throw new InvalidArgumentException(task);
            }

            // If pass all checking, create and return delete command obj
            assert task >= 0 : "Task should be non negative";
            return new DeleteCommand(task);

        case Parser.FIND_COMMAND:
            // Check for missing keyword argument
            if (commandComponents.length == 1) {
                throw new MissingArgumentException("Find", "keyword");
            }

            // Extract keyword argument
            keyword = commandComponents[1];

            // If pass all checking, create and return find command obj
            assert !keyword.equals("") : "Keyword should be specified";
            return new FindCommand(keyword);

        case Parser.DEADLINE_COMMAND:
            // Check for empty description
            if (commandComponents.length == 1) {
                throw new EmptyDescriptionException("Deadline");
            }
            arguments = fullCommand.split(" ", 2)[1];
            argumentComponents = arguments.split(" /by ");

            // Extract description argument
            description = argumentComponents[0];

            // Check for missing by argument
            if (argumentComponents.length == 1) {
                throw new MissingArgumentException("Deadline", "/by");
            }

            // Extract and parse by argument
            try {
                by = LocalDate.parse(arguments.split(" /by ")[1]);
            } catch (DateTimeParseException err) {
                throw new InvalidDateFormatException();
            }

            // If pass all checking, create and return deadline command obj
            assert !description.equals("") && by != null : "Description and by argument should be specified";
            return new DeadlineCommand(description, by);

        case Parser.EVENT_COMMAND:
            // Check for missing description
            if (commandComponents.length == 1) {
                throw new EmptyDescriptionException("Event");
            }
            arguments = fullCommand.split(" ", 2)[1];
            argumentComponents = arguments.split(" /at ");

            // Extract description argument
            description = argumentComponents[0];
            if (argumentComponents.length == 1) {
                throw new MissingArgumentException("Event", "/at");
            }

            // Extract at argument
            at = arguments.split(" /at ")[1];

            // If pass all checking, create and return event command obj
            assert !description.equals("") && at != null : "Description and at argument should be specified";
            return new EventCommand(description, at);

        case Parser.TODO_COMMAND:
            // Check for missing description
            if (commandComponents.length == 1) {
                throw new EmptyDescriptionException("Todo");
            }

            // Extract description argument
            description = fullCommand.split(" ", 2)[1];

            // If pass all checking, create and return event command obj
            assert !description.equals("") : "Description should be specified";
            return new TodoCommand(description);

        case Parser.LIST_COMMAND:
            return new ListCommand();

        case Parser.SORT_COMMAND:
            return new SortCommand();

        case Parser.EXIT_COMMAND:
            return new ExitCommand();

        default:
            throw new UnknownCommandException();
        }
    }
}
