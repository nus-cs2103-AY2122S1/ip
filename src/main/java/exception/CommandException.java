package main.java.exception;

/**
 * The CommandException Exception is thrown when a command after "/" is invalid.
 * Thrown by Event and Deadline.
 * @author  Hoon Darren
 * @version 1.0
 * @since   2021-08-21
 */
public class CommandException extends DukeException {

    /**
     * Constructor to create a new CommandException.
     * @param task task to be added.
     * @param command command required.
     */
    public CommandException(String task, String command) {
        super(String.format("Your task of %s requires the command %s", task, command));
    }
}
