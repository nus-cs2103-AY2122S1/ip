package memocat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import memocat.task.Deadline;
import memocat.task.Event;
import memocat.task.Task;
import memocat.task.Todo;


/**
 * A parser to parse memocat commands.
 */
public class Parser {
    /**
     * Gets the action specified by the command.
     *
     * A valid action can be list, done, todo, event, deadline, delete.
     *
     * @param command The command to be parsed.
     * @return The action of the command.
     */
    public String getCommandAction(String command) {
        return command.split(" ", 2)[0];
    }

    /**
     * Gets the task index of done and delete action.
     *
     * @param command The command to be parsed.
     * @return The index of the task of the command, -1 if is invalid action.
     */
    public int getCommandActionIndex(String command) throws MemoCatException {
        switch (getCommandAction(command)) {
        case "done":
            return Integer.parseInt(command.substring(5));
        case "delete":
            return Integer.parseInt(command.substring(7));
        default:
            throw new MemoCatException("Invalid action. Index cannot be parsed from the command.");
        }
    }

    /**
     * Gets the relevant task information from the command.
     *
     * @param command The command to be parsed.
     * @return A Task created from the command.
     */
    public Task commandToTask(String command) throws MemoCatException {
        switch (getCommandAction(command)) {
        case "todo":
            return commandToTodo(command);
        case "event":
            return commandToEvent(command);
        case "deadline":
            return commandToDeadline(command);
        default:
            throw new MemoCatException("Invalid action. Task cannot be parsed from the command.");
        }
    }

    /**
     * Gets the relevant todo information from the command.
     *
     * @param command The command to be parsed.
     * @return A Todo created from the command.
     */
    private Todo commandToTodo(String command) throws MemoCatException {
        String[] todoDetails = command.split(" ", 2);
        assert todoDetails.length == 2 : "Invalid todo format";

        String todoDescription;
        try {
            todoDescription = todoDetails[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            // no description
            String message = "☹ OOPS!!! The description of a todo cannot be empty.";
            throw new MemoCatException(message);
        }

        return new Todo(todoDescription);
    }

    /**
     * Gets the relevant event information from the command.
     *
     * @param command The command to be parsed.
     * @return An Event created from the command.
     */
    private Event commandToEvent(String command) throws MemoCatException {
        String[] eventDetails = command.split(" /at ");
        assert eventDetails.length == 2 : "Invalid event format";

        LocalDate at;
        try {
            at = this.stringToLocalDate(eventDetails[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            // no /at found in command
            String message = "OH NO!!! The time of an event cannot be empty.";
            throw new MemoCatException(message);
        }

        String eventDescription;
        try {
            eventDescription = eventDetails[0].split("event ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            // no event description
            String message = "OH NO!!! The description of an event cannot be empty.";
            throw new MemoCatException(message);
        }

        return new Event(eventDescription, at);
    }

    /**
     * Gets the relevant deadline information from the command.
     *
     * @param command The command to be parsed.
     * @return A Deadline created from the command.
     */
    private Deadline commandToDeadline(String command) throws MemoCatException {
        String[] deadlineDetails = command.split(" /by ");
        assert deadlineDetails.length == 2 : "Invalid deadline format";

        LocalDate by;
        try {
            by = this.stringToLocalDate(deadlineDetails[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            // no /by found in command
            String message = "☹ OOPS!!! The time of a deadline cannot be empty.";
            throw new MemoCatException(message);
        }

        String deadlineDescription;
        try {
            deadlineDescription = deadlineDetails[0].split("deadline ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            // no deadline description
            String message = "☹ OOPS!!! The description of a deadline cannot be empty.";
            throw new MemoCatException(message);
        }

        return new Deadline(deadlineDescription, by);
    }

    /**
     * Parses a string of time into LocalDate.
     *
     * @param str The time string.
     * @return The LocalDate corresponding to the string.
     * @throws MemoCatException The exception that contains the message to be printed.
     */
    public LocalDate stringToLocalDate(String str) throws MemoCatException {
        try {
            return LocalDate.parse(str);
        } catch (DateTimeParseException e) {
            String message = "The time format is invalid. Please use the format YYYY-MM-DD";
            throw new MemoCatException(message);
        }
    }

    /**
     * Formats LocalDate into MMMM d yyyy format.
     *
     * @param localDate The LocalDate to be formatted.
     * @return The formatted LocalDate string.
     */
    public String formatLocalDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
