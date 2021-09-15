package duke.Exceptions;

/**
 * Represents NoDescriptionException class
 */
public class NoDescriptionException extends Exception {

    /**
     * The Constructor for NoDescriptionException
     * @param message
     */
    public NoDescriptionException(String message) {
        super(String.format("☹ OOPS!!! The description of a " + message + " cannot be empty."));
    }
}

