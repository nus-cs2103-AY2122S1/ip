package duke.exception;

/**
 * A class to represent exceptions specific to Duke.
 */
public class DukeException extends Exception {

    /**
     * A public constructor for DukeException.
     *
     * @param message The error message for this exception.
     */
    public DukeException(String message) {

        super(message);
    }
}
