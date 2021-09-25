package command.exceptions;

/**
 * Exception to do with Command to be thrown when the user does not input
 * a tagger given the specified command.
 */
public class EmptyTaggerException extends RuntimeException {
    /**
     * Default constructor of EmptyTaggerException.
     *
     * @param errorMessage message to be stored in the EmptyTaggerException.
     */
    public EmptyTaggerException(String errorMessage) {
        super(errorMessage);
    }
}
