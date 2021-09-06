package duke.exception;

/**
 * Represents an exception related to Duke.
 */
public class DukeTaskNotFoundException extends DukeException {
    /**
     * Constructs a new DukeException with the specified detail message.
     */
    public DukeTaskNotFoundException() {
        super("You need to specify the task!\n");
    }
}
