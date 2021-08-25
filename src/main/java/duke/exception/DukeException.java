package duke.exception;

/**
 * Used to handle any errors in Duke
 */
public class DukeException extends Exception {

    /**
     * Constructor for the Error
     */
    protected DukeException() {
    }

    /**
     * Constructor for the Error with the return message
     *
     * @param message Message to be printed out to the users
     */
    public DukeException(String message) {
        super(message);
    }
}
