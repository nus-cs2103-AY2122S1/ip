package duke.exception;

/**
 * This class represents exceptions due to missing task.
 */
public class InvalidTaskException extends DukeException {
    /**
     * Constructor to create an invalid task exception.
     *
     * @param msg The error message of the exception.
     */
    public InvalidTaskException(String msg) {
        super(msg);
    }
}