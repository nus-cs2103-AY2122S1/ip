package main.java.exception;

/**
 * The InvalidCommandException Exception is thrown when an input is not part of the feature set.
 * @author  Hoon Darren
 * @version 1.0
 * @since   2021-08-21
 */
public class InvalidCommandException extends DukeException {
    /**
     * Exception when the command is invalid.
     */
    public InvalidCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}