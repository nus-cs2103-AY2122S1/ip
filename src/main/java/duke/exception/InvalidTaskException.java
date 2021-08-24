package duke.exception;

/**
 * A subclass of DukeException. Thrown when users do not give a valid task.
 *
 */
public class InvalidTaskException extends DukeException {
    /**
     * Constructor for the exception.
     */
    public InvalidTaskException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-( Please key in a valid task!");
    }
}
