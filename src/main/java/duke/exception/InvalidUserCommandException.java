package duke.exception;

/**
 * Represents an exception caused by a user input that Duke cannot parse.
 */
public class InvalidUserCommandException extends DukeException {
    /**
     * Constructor for InvalidUserCommandException.
     *
     * @param invalidInput Invalid user input string.
     */
    public InvalidUserCommandException(String invalidInput) {
        super("I'm sorry, but I don't know what " + invalidInput + " means :-(");
    }
}
