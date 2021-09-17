package duke.exception;

/**
 * Represents exceptions for Duke.
 */
public class DukeException extends Exception {

    /**
     * Parent for all Exceptions to be thrown.
     *
     * @param e The error thrown.
     */
    public DukeException(String e) {
        super(e);
    }
}

