package exception;

/**
 * Encapsulates the exception thrown when an input meant to be a task number cannot be parsed into a number.
 */
public class InvalidTaskNumberException extends DukeException {
    /**
     * Constructor to instantiate an `exception.InvalidTaskNumberException`.
     *
     * @param input is the invalid string input that cannot be parsed into a number.
     */
    public InvalidTaskNumberException(String input) {
        super(String.format("%s is not a valid number", input));
    }
}
