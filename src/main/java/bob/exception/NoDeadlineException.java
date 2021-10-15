package bob.exception;

/**
 * Represents the exception thrown by Bob when the user does not specify the deadline of the Deadline task.
 */
public class NoDeadlineException extends InvalidInputException {
    /**
     * Constructor for a new NoDeadlineException instance.
     */
    public NoDeadlineException() {
        super();
    }

    /**
     * Gets error message for when the user does not specify the deadline of their Deadline task.
     *
     * @return Error message.
     */
    @Override
    public String getMessage() {
        return "When is the deadline? >:(\nPlease specify the date with the format\n/by YYYY-MM-DD";
    }
}
