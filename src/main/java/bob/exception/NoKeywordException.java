package bob.exception;

/**
 * Represents the exception thrown by Bob when the user does not specify the keyword for their task search.
 */
public class NoKeywordException extends InvalidInputException {
    /**
     * Constructor for a new NoKeywordException instance.
     */
    public NoKeywordException() {
        super();
    }

    /**
     * Gets error message for when the user does not specify any keyword for their search.
     *
     * @return Error message.
     */
    @Override
    public String toString() {
        return "What are you even looking for >:(\n";
    }
}
