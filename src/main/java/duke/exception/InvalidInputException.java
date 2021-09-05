package duke.exception;

/**
 * Represents a DukeException that is thrown when the given input is invalid.
 */
public class InvalidInputException extends DukeException {

    /**
     * Constructor for InvalidInputException.
     */
    public InvalidInputException() {
        super("I'm sorry boss! I'm not quite sure what you need me to do!");
    }
}
