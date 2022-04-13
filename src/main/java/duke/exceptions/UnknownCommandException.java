package duke.exceptions;

/**
 * {@code UnknownCommandException} extends from {@code AuguryException}s.
 * Gets thrown when an unrecognized command is sent to {@code Augury}.
 */
public class UnknownCommandException extends AuguryException {
    public UnknownCommandException(String message) {
        super(message);
    }
}
