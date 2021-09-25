package command.exceptions;

/**
 * Exception to do with Command to be thrown when the user
 * specify invalid index from 1 to length of TaskList.
 */
public class InvalidIndexException extends RuntimeException {
    /**
     * Default constructor of InvalidIndexException.
     *
     * @param errorMessage message to be stored in the InvalidIndexException.
     */
    public InvalidIndexException(String errorMessage) {
        super(errorMessage);
    }
}
