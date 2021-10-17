package bob.exception;

/**
 * Represents the exception thrown by Bob when the user does not specify the task description.
 */
public class NoTaskException extends InvalidInputException {
    /**
     * Constructor for a new NoTaskException instance.
     */
    public NoTaskException() {
        super();
    }

    /**
     * Gets error message for when the user does not specify the task description.
     *
     * @return Error message.
     */
    @Override
    public String getMessage() {
        return "You didn't specify your task! >:(\n";
    }
}
