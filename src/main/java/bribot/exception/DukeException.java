package bribot.exception;

/**
 * Represents exceptions that are specific to the program.
 */

public class DukeException extends Exception {
    /**
     * creates a DukeException with the given error message.
     * @param errorMessage the given error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
