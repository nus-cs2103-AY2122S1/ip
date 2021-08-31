package duke.exceptions;

/**
 * Exception class to handle the unknown instructions to duke.
 */
public class UnknownException extends Exception {
    public UnknownException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
