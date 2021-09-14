package duke.exception;

/**
 * This is the DukeIoException class that extends from Exception
 * to catch the IO exception.
 */
public class DukeIoException extends Exception {

    /**
     * Constructs a DukeIoException object.
     *
     * @param message Exception message.
     */
    public DukeIoException(String message) {
        super(message);
    }
}
