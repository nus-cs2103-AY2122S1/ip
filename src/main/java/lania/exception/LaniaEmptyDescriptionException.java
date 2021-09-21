package lania.exception;

/**
 * Represents exception handling for invalid inputs with an empty description of task.
 */
public class LaniaEmptyDescriptionException extends LaniaException {

    /**
     * Constructor for the LaniaEmptyDescriptionException class.
     *
     * @param message The error message
     */
    public LaniaEmptyDescriptionException(String message) {
        super("The description of " + message + " cannot be empty");
    }
}
