package duke.exceptions;

/**
 * The condition where the user enters the find command without a query.
 */
public class EmptyQueryException extends DukeException {
    /**
     * Creates an empty query exception instance with a standard detail message.
     */
    public EmptyQueryException() {
        super("Please enter a query to find!");
    }
}
