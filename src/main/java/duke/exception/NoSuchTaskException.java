package duke.exception;

/**
 * Represents an exception caused when no such task is found for a command that needs a task number
 */
public class NoSuchTaskException extends DukeException {
    /**
     * Constructor for the exception.
     */
    public NoSuchTaskException() {
        super("Hey, there is no such task!");
    }
}
