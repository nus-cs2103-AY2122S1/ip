package lania;

import lania.command.AddCommand;
import lania.command.Command;
import lania.command.CompleteCommand;
import lania.command.DeleteCommand;
import lania.command.ExitCommand;
import lania.command.FindCommand;
import lania.command.ListCommand;
import lania.command.UndoCommand;
import lania.exception.LaniaEmptyDescriptionException;
import lania.exception.LaniaException;
import lania.task.Deadline;
import lania.task.Event;
import lania.task.Todo;

/**
 * This class deals with making sense of the user command
 * by parsing the given string.
 */
public class Parser {

    /**
     * Decides which command to execute based on the given input.
     *
     * @param fullCommand The input given by user.
     * @return The type of command to be executed.
     * @throws LaniaException If the command given matches none of the expected commands.
     */
    public Command parse(String fullCommand) throws LaniaException {
        String firstCommand = parseCommand(fullCommand);
        String taskDescription;
        String[] task;
        switch (firstCommand) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "undo":
            return new UndoCommand();
        case "find":
            return new FindCommand(parseTaskDescription(fullCommand));
        case "done":
            return new CompleteCommand(getIndex(fullCommand));
        case "delete":
            return new DeleteCommand(getIndex(fullCommand));
        case "todo":
            return new AddCommand(new Todo(parseTaskDescription(fullCommand)));
        case "deadline":
            taskDescription = parseTaskDescription(fullCommand);
            task = parseDeadlineDescription(taskDescription);
            return new AddCommand(new Deadline(task[0], task[1]));
        case "event":
            taskDescription = parseTaskDescription(fullCommand);
            task = parseEventDescription(taskDescription);
            return new AddCommand(new Event(task[0], task[1]));
        default:
            throw new LaniaException("Sorry, but Lania does not know what that means.");
        }
    }

    /**
     * Checks the first word of the given input.
     *
     * @param command The command given to the Lania.
     * @return The first word of the given input.
     */
    public String parseCommand(String command) {
        String[] split = command.split(" ");
        return split[0];
    }

    /**
     * Checks the description of the given task,
     * provided that the type of task is specified.
     *
     * @param command The command given to the Lania.
     * @return The description of the given task.
     * @throws LaniaEmptyDescriptionException If the task description is not given.
     */
    public String parseTaskDescription(String command) throws LaniaEmptyDescriptionException {
        String[] split = command.split(" ", 2);
        if (split.length == 1) {
            throw new LaniaEmptyDescriptionException(split[0]);
        }

        return split[1];
    }

    /**
     * Checks if the user input deadline is accompanied with the date and time.
     *
     * @param command The command given to the Lania.
     * @return An array of strings containing the task description and its date and time.
     * @throws LaniaEmptyDescriptionException If the date and time is not provided.
     */
    public String[] parseDeadlineDescription(String command) throws LaniaEmptyDescriptionException {
        String[] split = command.split(" /by ");
        if (split.length == 1) {
            throw new LaniaEmptyDescriptionException("date/time");
        }

        return split;
    }

    /**
     * Similar to parseDeadline, except it works for Event tasks.
     *
     * @param command The command given to the Lania.
     * @return An array of strings containing the task description and its date and time.
     * @throws LaniaEmptyDescriptionException If the date and time is not provided.
     */
    public String[] parseEventDescription(String command) throws LaniaEmptyDescriptionException {
        String[] split = command.split(" /at ");
        if (split.length == 1) {
            throw new LaniaEmptyDescriptionException("date/time");
        }

        return split;
    }

    /**
     * Gets the index of the task that the user wants to
     * perform an operation on.
     *
     * @param command The command given to the Lania.
     * @return The index of a task.
     */
    public int getIndex(String command) {
        String[] split = command.split(" ");
        return Integer.parseInt(split[1]);
    }
}
