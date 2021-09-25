package command.exceptions;

/**
 * Exception to do with Command to be thrown when the user does not input
 * an index given the specified command.
 */
public class EmptyIndexException extends RuntimeException {
    /**
     * Default constructor of EmptyIndexException.
     *
     * @param errorMessage message to be stored in the EmptyIndexException.
     */
    public EmptyIndexException(String errorMessage) {
        super(errorMessage);
    }
}
