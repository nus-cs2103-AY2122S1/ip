package duke.exceptions;

/**
 * The condition where the user does not specify the date
 * in the correct format.
 */
public class InvalidDateException extends DukeException {
    /**
     * Creates an invalid date exception instance with a
     * standard detail message.
     */
    public InvalidDateException() {
        super("The date is not in the format YYYY-MM-DD!");
    }
}
