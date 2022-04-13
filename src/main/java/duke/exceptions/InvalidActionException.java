package duke.exceptions;

/**
 * {@code InvalidActionException} extends from {@code AuguryException}s.
 * Gets thrown when invalid parameters are provided to commands.
 */
public class InvalidActionException extends AuguryException {
    public InvalidActionException(String message) {
        super(message);
    }
}
