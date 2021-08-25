package duke.exception;

/**
 * This class represents exceptions due to missing time.
 */
public class MissingTimeException extends DukeException {
    /**
     * Constructor to create a missing time exception.
     *
     * @param msg The error message of the exception.
     */
    public MissingTimeException(String msg) {
        super(msg);
    }
}