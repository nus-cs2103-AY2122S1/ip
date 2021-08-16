package duke.exception;

/**
 * Exception class to handle the wrong format for tasks without deadline.
 *
 * @author limzk126
 * @version Level-6
 */
public class InvalidNoDateException extends DukeException{
    /**
     * Constructor for InvalidNoDateException class.
     */
    public InvalidNoDateException() {
        super("Sorry >.< but this format is invalid!\nPlease follow this format: " +
                "[<Type> <Description>].");
    }
}
