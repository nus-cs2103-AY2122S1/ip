package duke.error;

/**
 * Represents a exception that would occur in a duke application.
 */
public class DukeException extends Exception {
    /**
     * Constructs a Duke Error.
     *
     * @param errorMessage The error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
