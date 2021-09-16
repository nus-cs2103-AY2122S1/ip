package duke.exception;

/**
 * Represents the invalid command exception.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Represents the error message.
     */
    private static String MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    /**
     * Constructs a InvalidCommandException object.
     */
    public InvalidCommandException() {
        super(MESSAGE);
    }
}
