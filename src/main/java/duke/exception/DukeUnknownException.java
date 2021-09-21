package duke.exception;

/**
 * This is the DukeUnknownException class that extends from Exception
 * to catch the unknown exception.
 */
public class DukeUnknownException extends Exception {

    /**
     * Constructs a DukeUnknownException object.
     *
     * @param message Exception message.
     */
    public DukeUnknownException(String message) {
        super(message);
    }
}
