package bruh.parser;

import bruh.command.AddTaskCommand;
import bruh.command.Command;
import bruh.command.DeleteTaskCommand;
import bruh.command.ExitCommand;
import bruh.command.ListTasksByDateCommand;
import bruh.command.ListTasksBySearchCommand;
import bruh.command.ListTasksCommand;
import bruh.command.MarkTaskDoneCommand;
import bruh.exception.BruhException;
import bruh.exception.InvalidArgumentException;
import bruh.exception.MissingArgumentException;
import bruh.task.Task;

/**
 * Parses strings into commands & performs validation of the arguments provided.
 */
public final class Parser {
    private static final String STOP_SIGNAL = "bye";
    private static final String EVENT_MISSING_ARG_ERROR_MSG =
            "The description of a %s cannot be empty.";
    private static final String DONE_MISSING_ARG_ERROR_MSG =
            "Please specify a task number to mark as done.";
    private static final String DELETE_MISSING_ARG_ERROR_MSG =
            "Please specify a task number for deletion.";
    private static final String LIST_MISSING_ARG_ERROR_MSG = "Please specify a valid flag.";
    private static final String FIND_MISSING_ARG_ERROR_MSG = "Please specify a search term.";

    private Parser() {}

    /**
     * Parses string input and returns the appropriate command to be run by the chatbot.
     *
     * @param input The input entered by the user.
     * @return The parsed command, to be run by the chatbot.
     * @throws BruhException If an invalid input cannot be parsed to a command.
     */
    public static Command parseInputToCommand(String input) throws BruhException {
        if (input.isEmpty()) {
            throw new InvalidArgumentException();
        } else if (input.equals(STOP_SIGNAL)) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListTasksCommand();
        }
        return parseMultiWordCommand(input);
    }

    /**
     * Parses complex commands containing more than one argument.
     *
     * @param input The multi-word input, to be parsed to a command.
     * @return The parsed command, to be run by the chatbot.
     * @throws BruhException If an invalid input cannot be parsed to a command.
     */
    private static Command parseMultiWordCommand(String input) throws BruhException {
        String[] inputs = input.split(" ", 2);
        String keyword = inputs[0];

        switch (keyword) {
        // Fallthrough
        case "todo":
        case "deadline":
        case "event":
            checkMissingArguments(inputs, String.format(EVENT_MISSING_ARG_ERROR_MSG, keyword));
            Task newTask = Task.createTask(inputs);
            return new AddTaskCommand(newTask);
        case "done":
            checkMissingArguments(inputs, DONE_MISSING_ARG_ERROR_MSG);
            int indexToMarkDone = Integer.parseInt(inputs[1]) - 1;
            return new MarkTaskDoneCommand(indexToMarkDone);
        case "delete":
            checkMissingArguments(inputs, DELETE_MISSING_ARG_ERROR_MSG);
            int indexToDelete = Integer.parseInt(inputs[1]) - 1;
            return new DeleteTaskCommand(indexToDelete);
        case "list":
            checkMissingArguments(inputs, LIST_MISSING_ARG_ERROR_MSG);
            return new ListTasksByDateCommand(inputs[1]);
        case "find":
            checkMissingArguments(inputs, FIND_MISSING_ARG_ERROR_MSG);
            return new ListTasksBySearchCommand(inputs[1]);
        default:
            throw new InvalidArgumentException();
        }
    }

    /**
     * Checks for missing arguments & throws an exception, if found.
     *
     * @param sections An array consisting of the input, split into the first word and the rest of
     *        the input
     * @param errorMessage The error message to be displayed, if an exception is thrown.
     * @throws MissingArgumentException if a missing argument is found.
     */
    public static void checkMissingArguments(String[] sections, String errorMessage)
            throws MissingArgumentException {
        if (sections.length != 2 || sections[1].isEmpty()) {
            throw new MissingArgumentException(errorMessage);
        }
    }
}
