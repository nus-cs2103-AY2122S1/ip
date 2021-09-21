package duke.exception;

/**
 * This is the DukeExtractCommandException class that extends from Exception
 * to catch the extract command exception.
 */
public class DukeExtractCommandException extends Exception {

    /**
     * Constructs a DukeExtractCommandException object.
     *
     * @param message Exception message.
     */
    public DukeExtractCommandException(String message) {
        super(message);
    }
}
