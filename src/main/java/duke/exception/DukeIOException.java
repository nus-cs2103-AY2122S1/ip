package duke.exception;

/**
 * The is the DukeIOException class that extends from Exception
 * to catch the IO exception.
 */
public class DukeIOException extends Exception {

    /**
     * Constructs a DukeIOException object.
     *
     * @param message Exception message.
     */
    public DukeIOException(String message) {
        super(message);
    }
}
