package duke.exception;

/**
 * Represents exception thrown when task number is invalid,
 * eg. -1 or out of bounds.
 */
public class TaskNotFoundException extends DukeException {

    /**
     * Initialise constructor for {@code TaskNotFoundException}.
     */
    public TaskNotFoundException() {
        super("Invalid task number, please try again.");
    }
}
