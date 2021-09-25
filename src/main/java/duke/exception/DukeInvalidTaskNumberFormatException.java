package duke.exception;

/**
 * Represents an exception related to Duke.
 */
public class DukeInvalidTaskNumberFormatException extends DukeException {
    /**
     * Constructs a new DukeException with the specified detail message.
     */
    public DukeInvalidTaskNumberFormatException() {
        super("Please use the task number instead of task name!\n");
    }
}
