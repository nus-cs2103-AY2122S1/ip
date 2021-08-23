package duke.exception;

/**
 * Handles custom Duke chat bot exceptions.
 */
public class DukeException extends Exception {
    /**
     * Constructs DukeException object.
     *
     * @param error error message.
     */
    public DukeException(String error) {
        super(error);
    }
}
