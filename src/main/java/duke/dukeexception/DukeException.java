package duke.dukeexception;

/**
 * Represents the overarching exception for any problems related to Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs the DukeException from a message.
     *
     * @param message The given error message.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Constructs the DukeException from an exception.
     *
     * @param e The exception that can be converted into a Duke Exception.
     */
    public DukeException(Exception e) {
        super(e);
    }
}
