package duke.exception;

/**
 * Represents an exception thrown when list is empty.
 */
public class EmptyListException extends DukeException {

    /**
     * Initialise constructor for the exception {@code EmptyListException}.
     */
    public EmptyListException() {
        super("Nee's list is empty! Add some tasks?");
    }
}
