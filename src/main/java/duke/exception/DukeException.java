package duke.exception;

/**
 * Custom class for exceptions related to duke.Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs a duke.exception.DukeException object.
     *
     * @param error Error message for the exception.
     */
    public DukeException(String error) {
        super(error);
    }
}
