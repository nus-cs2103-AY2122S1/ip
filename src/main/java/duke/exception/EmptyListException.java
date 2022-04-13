package duke.exception;

/**
 * Exception of Duke that occurs when attempting to access an empty task list.
 */
public class EmptyListException extends DukeException {
    public EmptyListException() {
        super("The list is currently empty!");
    }
}
