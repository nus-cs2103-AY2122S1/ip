package duke.data.exception;

/**
 * Class that signals an error that Duke may encounter.
 *
 * @author Won Ye Ji
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException.
     *
     * @param message should contain relevant information on the failed execution(s).
     */
    public DukeException(String message) {
        super(message);
    }
}
