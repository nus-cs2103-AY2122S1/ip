package duke.parser;

import java.util.Arrays;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.Commands;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.PrioritizeCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Priority;
import duke.task.Todo;

/** A parser class that parses user's commands. */
public class Parser {
    private static Commands getCommand(String input) {
        String[] splitString = input.split(" ", 2);
        String command = splitString[0];

        return Commands.of(command);
    }

    /** Returns the param up to the given limit number. */
    private static String[] getParams(String input, int limit) {
        String[] splitString = input.split(" ", limit + 1);
        return Arrays.copyOfRange(splitString, 1, splitString.length);
    }

    private static Command parseList() {
        return new ListCommand();
    }

    private static Command parseExit() {
        return new ExitCommand();
    }

    private static Command parseFind(String input) {
        String keyword = getParams(input, 1)[0];
        return new FindCommand(keyword);
    }

    private static Command parseDone(String input) {
        int index = Integer.parseInt(getParams(input, 1)[0]);
        return new MarkCommand(index);
    }

    private static Command parseDelete(String input) {
        int index = Integer.parseInt(getParams(input, 1)[0]);
        return new DeleteCommand(index);
    }

    private static Command parseTodo(String input) {
        String description = getParams(input, 1)[0];
        return new AddCommand(new Todo(description));
    }

    private static Command parseDeadline(String input) throws DukeException {
        String param = getParams(input, 1)[0];

        if (!param.contains(" /by ")) {
            throw new DukeException("Invalid format for `deadline` command. '/by' keyword is needed");
        }

        String[] params = param.split(" /by ");
        String description = params[0];
        String by = params[1];
        return new AddCommand(new Deadline(description, by));
    }

    private static Command parseEvent(String input) throws DukeException {
        String param = getParams(input, 1)[0];

        if (!param.contains(" /at ")) {
            throw new DukeException("Invalid format for `event` command. '/at' keyword is needed");
        }

        String[] params = param.split(" /at ");
        String description = params[0];
        String at = params[1];

        return new AddCommand(new Event(description, at));
    }

    private static Command parsePrioritize(String input) throws DukeException {
        String[] params = getParams(input, 2);

        int taskNum = Integer.parseInt(params[0]);
        Priority priority = Priority.of(params[1]);

        return new PrioritizeCommand(taskNum, priority);
    }

    /**
     * Parses the input into its corresponding commands.
     *
     * @param input The string command.
     * @return The corresponding Command object.
     * @throws DukeException If the command is invalid.
     */
    public static Command parse(String input) throws DukeException {
        Commands commandType = getCommand(input);

        if (commandType == null) {
            throw new DukeException("Invalid command: " + input);
        }

        switch (commandType) {
        case LIST:
            return parseList();
        case BYE:
            return parseExit();
        case FIND:
            return parseFind(input);
        case DONE:
            return parseDone(input);
        case DELETE:
            return parseDelete(input);
        case TODO:
            return parseTodo(input);
        case DEADLINE:
            return parseDeadline(input);
        case EVENT:
            return parseEvent(input);
        case PRIORITIZE:
            return parsePrioritize(input);
        default:
            return null;
        }
    }
}
