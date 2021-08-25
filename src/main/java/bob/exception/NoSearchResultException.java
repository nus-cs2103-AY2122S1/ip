package bob.exception;

/**
 * Represents the exception thrown by Bob when there is no task in the task list that corresponds to their search.
 */
public class NoSearchResultException extends BobException {
    /**
     * Constructor for a new NoSearchResultException instance.
     */
    public NoSearchResultException() {
        super();
    }
}
