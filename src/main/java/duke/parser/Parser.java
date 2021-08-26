package duke.parser;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.DeleteTaskCommand;
import duke.command.ExitCommand;
import duke.command.ListTasksByDateCommand;
import duke.command.ListTasksCommand;
import duke.command.MarkTaskDoneCommand;
import duke.exception.BruhException;
import duke.exception.InvalidArgumentException;
import duke.exception.MissingArgumentException;
import duke.task.Task;

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
            Parser.checkMissingArguments(inputs, String.format("The description of a %s cannot be empty.", keyword));
            Task newTask = Task.createTask(inputs);
            return new AddTaskCommand(newTask);
        }
        case "done": {
            Parser.checkMissingArguments(inputs, "Please specify a task number to mark as done.");
            int index = Integer.parseInt(inputs[1]) - 1;
            return new MarkTaskDoneCommand(index);
        }
        case "delete": {
            Parser.checkMissingArguments(inputs, "Please specify a task number for deletion.");
            int index = Integer.parseInt(inputs[1]) - 1;
            return new DeleteTaskCommand(index);
        }
        case "list": {
            Parser.checkMissingArguments(inputs, "Please specify a valid flag.");
            String remainingInput = inputs[1];
            return new ListTasksByDateCommand(remainingInput);
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
