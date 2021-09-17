package exceptions;

/**
 * Exception raised when the command input by the user is not recognised.
 *
 * @author Quan Teng Foong
 */
public class NoSuchCommandException extends DukeException {
    public NoSuchCommandException(String errorMessage) {
        super(errorMessage);
    }
}
