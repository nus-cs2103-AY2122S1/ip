package duke.exception;

/**
 * Deals with out of bounds exception.
 */
public class OutOfBoundsException extends DukeException {
    /**
     * Constructor of EmptyValueException.
     */
    public OutOfBoundsException() {
        super("Input number is not within task length.");
    }
}
