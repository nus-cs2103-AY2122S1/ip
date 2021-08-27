package duke.exception;

/**
 * Deals with commands that do not follow format.
 */
public class OutOfBoundsException extends DukeException {
    /**
     * Constructor of EmptyValueException.
     */
    public OutOfBoundsException() {
        super("Input number is not within task length.");
    }
}
