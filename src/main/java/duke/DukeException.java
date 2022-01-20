package duke;

/**
 * Represents an expected exception that occurred within the operation of the Duke application.
 */
public class DukeException extends RuntimeException {

    /**
     * Constructor for DukeException
     *
     * @param errorMessage Error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
