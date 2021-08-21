package exception;

/**
 * Encapsulates an exception when an input meant to be a task number cannot be parsed into a number.
 */
public class InvalidTaskNumberException extends DukeException {
    public InvalidTaskNumberException(String input) {
        super(String.format("%s is not a valid number", input));
    }
}
