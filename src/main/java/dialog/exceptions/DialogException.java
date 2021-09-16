package dialog.exceptions;

/**
 * Exception to do with Dialog to be thrown when a Dialog of existing id
 * is to be generated.
 */
public class DialogException extends Exception {
    /**
     * Default constructor of DialogException.
     *
     * @param errorMessage message to be stored in the DialogException.
     */
    public DialogException(String errorMessage) {
        super(errorMessage);
    }
}
