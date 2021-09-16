package duke.exception;

/**
 * Thrown when the user tries to access an invalid task index.
 */
public class InvalidTaskIndexException extends DukeException {
    public InvalidTaskIndexException() {
        super("Invalid task index!");
    }
}
