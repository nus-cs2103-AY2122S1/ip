package duke;

/**
 * Exception specifically for the Duke class.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException
     *
     * @param message the error message.
     */
    DukeException(String message) {
        super(message);
    }
}
