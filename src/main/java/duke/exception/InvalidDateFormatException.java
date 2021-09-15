package duke.exception;

/**
 * An exception that handles command description with invalid date format.
 */
public class InvalidDateFormatException extends DukeException {

    private static final String message = "Your date (after \"%s\") is not in YYYY-MM-DD format!";

    /**
     * Constructs an InvalidDateFormatException instance that handles command description with invalid date format.
     *
     * @param argument The argument before the date String.
     */
    public InvalidDateFormatException(String argument) {
        super(String.format(message, argument));
    }
}
