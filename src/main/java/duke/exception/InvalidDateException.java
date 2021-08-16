package duke.exception;

/**
 * Exception class to handle wrong format for deadlined task.
 *
 * @author limzk126
 * @version Level-5
 */
public class InvalidDateException extends DukeException {
    /**
     * Constructor for InvalidDateException class.
     */
    public InvalidDateException() {
        super("Sorry >.< but this format is invalid!\nPlease follow this format:"
                + " [<Type> <Description> / <Date/Time>].");
    }
}
