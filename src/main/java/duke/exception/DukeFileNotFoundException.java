package duke.exception;

/**
 * Represents an exception related to Duke.
 */
public class DukeFileNotFoundException extends DukeException {
    /**
     * Constructs a new DukeException with the specified detail message.
     */
    public DukeFileNotFoundException() {
        super("Note: results are not saved to disk!\n");
    }
}
