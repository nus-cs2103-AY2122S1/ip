package duke.exception;

public class DukeInvalidCommandException extends Exception {
    /**
     * Constructor for invalid commands in duke.logic.Duke
     *
     * @param message The error message.
     */
    public DukeInvalidCommandException(String message) {
        super(message);
    }
}
