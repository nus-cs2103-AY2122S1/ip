package duke.core;

/**
 * DukeException encapsulates Exceptions specific to Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException object.
     *
     * @param message The detailed message of the exception encountered.
     */
    public DukeException(String message) {
        super(message);
    }
}
