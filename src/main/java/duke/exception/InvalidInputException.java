package duke.exception;

/**
 * Represents a DukeException that is thrown when the user input is completely invalid.
 */
public class InvalidInputException extends DukeException {
    /**
     * Constructs an InvalidInputException.
     */
    public InvalidInputException() {
        super("Meow! That is not a valid input!");
    }
}
