package duke.exception;

/**
 * Represents an exception thrown when list is empty.
 */
public class EmptyListException extends DukeException {
    public EmptyListException() {
        super("Nee's list is empty! Add some tasks?");
    }
}
