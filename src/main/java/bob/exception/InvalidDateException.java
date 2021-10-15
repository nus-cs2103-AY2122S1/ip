package bob.exception;

/**
 * Represents the exception thrown by Bob when the user inputs an invalid date as a deadline or event timing.
 */
public class InvalidDateException extends InvalidInputException {
    /**
     * Constructor for a new InvalidDateException instance.
     */
    public InvalidDateException() {
        super();
    }

    /**
     * Gets error message for when the user does not input a valid date for their deadline or event timing.
     *
     * @return Error message.
     */
    @Override
    public String getMessage() {
        return "What kind of date is that >:(\n";
    }
}
