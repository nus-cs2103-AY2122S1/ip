package main.java.exception;

public class InvalidCommandException extends DukeException {
    /**
     * Exception when the command is invalid.
     */
    public InvalidCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}