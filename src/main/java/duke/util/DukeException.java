package duke.util;

/**
 * A class that represents exceptions related to the running of Duke.
 */
public class DukeException extends RuntimeException {
    /**
     * Creates a new DukeException with a custom message.
     *
     * @param errorMessage A string representing an error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
