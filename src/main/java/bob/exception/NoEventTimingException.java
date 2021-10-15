package bob.exception;

/**
 * Represents the exception thrown by Bob when the user does not specify the timing of the Event task.
 */
public class NoEventTimingException extends InvalidInputException {
    /**
     * Constructor for a new NoEventTimingException instance.
     */
    public NoEventTimingException() {
        super();
    }

    /**
     * Gets error message for when the user does not specify the timing of their Event task.
     *
     * @return Error message.
     */
    @Override
    public String getMessage() {
        return "When is the event? >:(\nPlease specify the date with the format\n/at YYYY-MM-DD";
    }
}
