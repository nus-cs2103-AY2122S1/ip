package duke.exception;

/**
 * A DukeException that occurs when an unknown command is given.
 */
public class InvalidTaskException extends DukeException {

    /**
     * Public constructor to create an InvalidTaskException.
     */
    public InvalidTaskException() {
        super("Invalid Command");
    }
}
