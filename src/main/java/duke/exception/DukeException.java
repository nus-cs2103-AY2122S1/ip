package duke.exception;

/**
 * Represents exceptions for Duke.
 */
public class DukeException extends Exception {
    /**
     * Parent exception class.
     * @param e Error thrown.
     */
    public DukeException(String e) {
        super(e);
    }
}

