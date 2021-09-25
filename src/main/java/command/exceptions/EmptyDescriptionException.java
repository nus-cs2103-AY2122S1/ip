package command.exceptions;

/**
 * Exception to do with Command to be thrown when the user does not input
 * a description when trying to create a task.
 */
public class EmptyDescriptionException extends RuntimeException {
    /**
     * Default constructor of EmptyDescriptionException.
     *
     * @param errorMessage message to be stored in the EmptyDescriptionException.
     */
    public EmptyDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}

