package duke.exception;

/**
 * The EmptyDescriptionException is thrown when user attempts to add a task without descriptions.
 *
 * @author Benedict Chua
 */
public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException() {
        super("BAKA! The description of a task cannot be empty!");
    }
}

