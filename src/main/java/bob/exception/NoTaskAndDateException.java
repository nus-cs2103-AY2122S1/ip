package bob.exception;

/**
 * Represents the exception thrown by Bob when the user does not specify the task description
 * and event or deadline date.
 */
public class NoTaskAndDateException extends InvalidInputException {
    /**
     * Constructor for a new NoTaskAndDateException instance.
     */
    public NoTaskAndDateException() {
        super();
    }

    /**
     * Gets error message for when the user does not specify the task description and date of event or deadline.
     *
     * @return Error message.
     */
    @Override
    public String getMessage() {
        return "You didn't specify your task OR its date! >:(\n";
    }
}
