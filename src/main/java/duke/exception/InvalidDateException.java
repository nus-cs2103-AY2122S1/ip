package duke.exception;

/**
 * Exception class to handle the wrong format for dates.
 *
 * @author limzk126
 * @version Level-7
 */
public class InvalidDateException extends DukeException{
    /**
     * Constructor for InvalidNoDateException class.
     */
    public InvalidDateException() {
        super("Sorry >.< but this format is invalid!\nPlease follow this format: " +
                "[yyyy-MM-dd].");
    }
}
