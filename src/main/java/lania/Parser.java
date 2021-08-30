package lania;

import lania.command.AddCommand;
import lania.command.Command;
import lania.command.CompleteCommand;
import lania.command.DeleteCommand;
import lania.command.ExitCommand;
import lania.command.FindCommand;
import lania.command.ListCommand;
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
     */
    public Command parse(String fullCommand) {
        String firstCommand = parseCommand(fullCommand);
        if (firstCommand.equals("bye")) {
            return new ExitCommand();
        } else if (firstCommand.equals("list")) {
            return new ListCommand();
        } else if (firstCommand.equals("find")) {
            return new FindCommand(parseTaskDescription(fullCommand));
        } else if (firstCommand.equals("done")) {
            return new CompleteCommand(getIndex(fullCommand));
        } else if (firstCommand.equals("delete")) {
            return new DeleteCommand(getIndex(fullCommand));
        } else {
            if (firstCommand.equals("todo")) {
                return new AddCommand(new Todo(parseTaskDescription(fullCommand)));
            } else if (firstCommand.equals("deadline")) {
                String taskDescription = parseTaskDescription(fullCommand);
                String[] task = parseDeadline(taskDescription);
                return new AddCommand(new Deadline(task[0], task[1]));
            } else if (firstCommand.equals("event")) {
                String taskDescription = parseTaskDescription(fullCommand);
                String[] task = parseEvent(taskDescription);
                return new AddCommand(new Event(task[0], task[1]));
            } else {
                throw new LaniaException("Sorry, but Lania does not know what that means.");
            }
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
        } else {
            return split[1];
        }
    }

    /**
     * Checks if the user input deadline is accompanied with the date and time.
     *
     * @param command The command given to the Lania.
     * @return An array of strings containing the task description and its date and time.
     * @throws LaniaEmptyDescriptionException If the date and time is not provided.
     */
    public String[] parseDeadline(String command) throws LaniaEmptyDescriptionException {
        String[] split = command.split(" /by ");
        if (split.length == 1) {
            throw new LaniaEmptyDescriptionException("date/time");
        } else {
            return split;
        }
    }

    /**
     * Similar to parseDeadline, except it works for Event tasks.
     *
     * @param command The command given to the Lania.
     * @return An array of strings containing the task description and its date and time.
     * @throws LaniaEmptyDescriptionException If the date and time is not provided.
     */
    public String[] parseEvent(String command) throws LaniaEmptyDescriptionException {
        String[] split = command.split(" /at ");
        if (split.length == 1) {
            throw new LaniaEmptyDescriptionException("date/time");
        } else {
            return split;
        }
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
