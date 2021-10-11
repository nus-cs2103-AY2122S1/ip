package duke.exceptions;

/**
 * Represents an exception due to the user using an invalid
 * word.
 */
public class InvalidInputException extends DukeException {
    /** Constructor for <code>InvalidInputException</code> */
    public InvalidInputException() {
        super("OOPS!!! I'm sorry, but I don't know what that means.\n*sad quack*\n");
    }
}
