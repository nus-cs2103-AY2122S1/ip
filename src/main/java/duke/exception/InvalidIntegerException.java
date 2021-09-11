package duke.exception;

/**
 * Class that encapsulates an invalid integer Duke-specific exception.
 */
public class InvalidIntegerException extends DukeException {

    /**
     * Constructs a InvalidIntegerException instance.
     */
    public InvalidIntegerException() {
        super("Enter valid integer!");
    }
}
