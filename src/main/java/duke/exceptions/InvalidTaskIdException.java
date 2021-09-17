package duke.exceptions;

/**
 * The condition where the user specifies a task index that is invalid.
 */
public class InvalidTaskIdException extends DukeException {
    /**
     * Creates an invalid task exception instance with a standard detail message.
     */
    public InvalidTaskIdException() {
        super("I couldn't find a task with that ID. Please check again!");
    }
}
