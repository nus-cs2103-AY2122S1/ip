package duke.exception;

/**
 * This is the DukeTaskNumberOutOfBoundsException class that extends from Exception
 * to catch the task number out of bounds exception.
 */
public class DukeTaskNumberOutOfBoundsException extends Exception {

    /**
     * Constructs a DukeTaskNumberOutOfBoundsException object.
     *
     * @param message Exception message.
     */
    public DukeTaskNumberOutOfBoundsException(String message) {
        super(message);
    }
}
