package lebron.exception;

/**
 * Represents exceptions that are specific to the program.
 */

public class LebronException extends Exception {
    /**
     * Creates a DukeException with the given error message.
     *
     * @param errorMessage the error message.
     */
    public LebronException(String errorMessage) {
        super(errorMessage);
    }
}
