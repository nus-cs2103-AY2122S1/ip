package duke;

import duke.command.AddTaskCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteTaskCommand;
import duke.command.FindTasksCommand;
import duke.command.InvalidCommand;
import duke.command.ListTasksCommand;
import duke.command.MarkTaskAsDoneCommand;
import duke.command.RemindCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a class that deals with making sense of the user command.
 *
 * @author botr99
 */
public class Parser {

    /**
     * Parses a given string to an integer.
     *
     * @param integerString The string to be parsed.
     * @return An integer parsed from the string.
     * @throws DukeException When the string to be parsed does not consist of an integer.
     */
    public static int parseToInt(String integerString) throws DukeException {
        try {
            return Integer.parseInt(integerString);
        } catch (NumberFormatException e) {
            throw new DukeException("Oops!!! The command should be followed by an integer.");
        }
    }

    /**
     * Parses the user input of a task creation and returns the task
     * corresponding to the user input.
     *
     * @param description The user input.
     * @return The task created from the user input.
     * @throws DukeException When the description is null or missing.
     */
    public static Task parseTask(String description) throws DukeException {
        if (description == null || description.equals("")) {
            throw new DukeException("Oops!!! The description of a todo cannot be empty.");
        }
        return new Todo(description);
    }

    /**
     * Parses the user input of a date task creation and returns the date task
     * corresponding to the user input.
     *
     * @param descriptionAndDate The user input.
     * @param command The type of date task to be created.
     * @return Null if the command is not an appropriate date task type;
     *         the new date task created otherwise;
     * @throws DukeException When the description and date string cannot be split in 2;
     *                       when the date task cannot be constructed due to invalid date string.
     */
    public static Task parseDateTask(String descriptionAndDate, String command) throws DukeException {
        String[] splitDescriptionAndDate;
        Task task = null;

        try {
            if (command.equals("deadline")) {
                splitDescriptionAndDate = descriptionAndDate.split(" /by ");
                task = new Deadline(splitDescriptionAndDate[0].trim(), splitDescriptionAndDate[1].trim());
            } else if (command.equals("event")) {
                splitDescriptionAndDate = descriptionAndDate.split(" /at ");
                task = new Event(splitDescriptionAndDate[0].trim(), splitDescriptionAndDate[1].trim());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Oops!!! Deadlines or events should contain a description, followed by "
                    + "a /by or /at respectively, followed by a date.");
        }

        if (task == null) {
            throw new DukeException("Invalid command to create a deadline or event.");
        }

        return task;
    }

    /**
     * Parses the user input.
     *
     * @param userInput A string the user has inputted.
     * @return A Command corresponding to the user input.
     * @throws DukeException When an exception occurs when parsing tasks.
     */
    public static Command parseUserInput(String userInput) throws DukeException {
        String[] userInputSplit = userInput.trim().split(" ", 2);
        String commandString = userInputSplit[0];
        String action = userInputSplit.length == 2 ? userInputSplit[1].trim() : "";

        switch (commandString) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListTasksCommand();
        case "done":
            return new MarkTaskAsDoneCommand(action);
        case "delete":
            return new DeleteTaskCommand(action);
        case "todo":
            Task task = parseTask(action);
            return new AddTaskCommand(task);
        case "deadline":
        case "event":
            Task dateTask = parseDateTask(action, commandString);
            return new AddTaskCommand(dateTask);
        case "find":
            return new FindTasksCommand(action);
        case "remind":
            int numberOfDays = parseToInt(action);
            return new RemindCommand(numberOfDays);
        default:
            return new InvalidCommand();
        }
    }

}
