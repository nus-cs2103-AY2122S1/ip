package duke.exception;

/**
 * Represents an exception related to Duke.
 */
public class DukeTaskNumberOutOfBoundsException extends DukeException {
    /**
     * Constructs a new DukeException with the specified detail message.
     */
    public DukeTaskNumberOutOfBoundsException() {
        super("I'm sorry, but that task number is out of range.\n");
    }
}
