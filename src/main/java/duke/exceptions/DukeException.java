package duke.exceptions;

/**
 * Represents Exceptions that the Duke program encounters.
 *
 * @author ruiquan
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the given error message.
     *
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
