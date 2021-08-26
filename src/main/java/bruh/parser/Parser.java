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
 * Parses strings into commands & performs validation of
 * the arguments provided.
 */
public class Parser {
    private static final String STOP_SIGNAL = "bye";

    /**
     * Parses string input and returns the appropriate command
     * to be run by the chatbot.
     *
     * @param input The input entered by the user.
     * @return The command to be run by the chatbot.
     */
    public static Command parseInputToCommand(String input) throws BruhException {
        if (input.isEmpty()) {
            throw new InvalidArgumentException();
        } else if (input.equals(STOP_SIGNAL)) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListTasksCommand();
        }

        String[] inputs = input.split(" ", 2);
        String keyword = inputs[0];

        switch (keyword) {
        case "todo":
        case "deadline":
        case "event": {
            checkMissingArguments(inputs, String.format("The description of a %s cannot be empty.", keyword));
            Task newTask = Task.createTask(inputs);
            return new AddTaskCommand(newTask);
        }
        case "done": {
            checkMissingArguments(inputs, "Please specify a task number to mark as done.");
            int index = Integer.parseInt(inputs[1]) - 1;
            return new MarkTaskDoneCommand(index);
        }
        case "delete": {
            checkMissingArguments(inputs, "Please specify a task number for deletion.");
            int index = Integer.parseInt(inputs[1]) - 1;
            return new DeleteTaskCommand(index);
        }
        case "list": {
            checkMissingArguments(inputs, "Please specify a valid flag.");
            String remainingInput = inputs[1];
            return new ListTasksByDateCommand(remainingInput);
        }
        case "find": {
            checkMissingArguments(inputs, "Please specify a search term.");
            String remainingInput = inputs[1];
            return new ListTasksBySearchCommand(remainingInput);
        }
        default: {
            throw new InvalidArgumentException();
        }
        }
    }

    /**
     * Checks for missing arguments & throws an exception, if found.
     *
     * @param sections     An array consisting of the input, split into the first word and the rest of the input
     * @param errorMessage The error message to be displayed, if an exception is thrown.
     * @throws MissingArgumentException if a missing argument is found.
     */
    public static void checkMissingArguments(String[] sections, String errorMessage) throws MissingArgumentException {
        if (sections.length != 2 || sections[1].isEmpty()) {
            throw new MissingArgumentException(errorMessage);
        }
    }
}
