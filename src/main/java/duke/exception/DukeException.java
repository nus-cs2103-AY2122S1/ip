package duke.exception;

/**
 * Represents all exceptions specific to the Duke program.
 */
public class DukeException extends Exception {
    /**
     * Constructor for the exception.
     * @param errorMessage The error message;
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
