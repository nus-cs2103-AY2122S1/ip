package bern.exception;

/**
 * An exception for empty description.
 */
public class EmptyDescriptionException extends BernException {
    /**
     * Constructor for EmptyDescriptionException.
     *
     * @param command The command whose description is empty.
     */
    public EmptyDescriptionException(String command) {
        super("the description of a " + command + " cannot be empty.");
    }
}
