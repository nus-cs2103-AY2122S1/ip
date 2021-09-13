package duke.exception;

/**
 * Represents the specific exception that has the index out of range.
 *
 * @author QIN GUORUI
 */
public class OutOfRangeException extends DukeException {
    /**
     * Initialises the out of range exception.
     *
     * @param message The message specified what is out of range.
     */
    public OutOfRangeException(String message) {
        super("The index for " + message + " is out of range, no task of that index is there."
                + " Please choose another one.");
    }
}
