package duke.exception;

/**
 * Custom class for exceptions related to Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor for the duke.exception.DukeException class.
     *
     * @param error Error message for the exception.
     */
    public DukeException(String error) {
        super(error);
    }
}
