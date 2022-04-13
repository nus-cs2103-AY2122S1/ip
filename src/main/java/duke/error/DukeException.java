package duke.error;

/**
 * Exceptions that can occur specifically in the duke.Duke program.
 */
public class DukeException extends Exception {
    /**
     * Constructor of the duke.error.DukeException
     * @param message Message to be printed out to user when error has occurred.
     */
    public DukeException(String message) {
        super(message);
    }
}
