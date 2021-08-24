package duke.exception;

/**
 * A class which contains all exceptions thrown in the program.
 *
 */
public class DukeException extends Exception {
    /**
     * Constructor of an exception.
     * @param message Message to be displayed to users.
     */
    public DukeException(String message) {
        super(message);
    }
}
