package duke.exception;

/**
 * Exception class to handle the wrong format for dates.
 */
public class InvalidDateException extends DukeException {
    /**
     * Constructor for InvalidNoDateException class.
     */
    public InvalidDateException() {
        super("Sorry >.< but this date format is invalid!\nPlease follow this format: "
                + "[yyyy-MM-dd].");
    }
}
