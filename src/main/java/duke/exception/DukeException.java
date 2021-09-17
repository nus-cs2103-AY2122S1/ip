package duke.exception;

/**
 * The DukeException is an exception that is thrown when an error happens when Duke is running.
 *
 * It contains the exceptions that are thrown due to wrong inputs given and is specific to this version of Duke.
 *
 * @author Benedict Chua
 */
public class DukeException extends RuntimeException {
    private String message;

    /**
     * Creates for DukeException.
     *
     * @param message String containing the error message to be shown to the user.
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
