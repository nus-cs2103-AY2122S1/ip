package duke.exception;

/**
 * Represents a Duke exception that is thrown when the task to be added is a duplicate
 * of another already in the tasklist.
 */
public class DuplicateTaskException extends DukeException {

    /**
     * Constructor for DuplicateTaskException.
     */
    public DuplicateTaskException() {
        super("You've already added that task boss!");
    }
}
