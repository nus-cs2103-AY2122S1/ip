package bobbybot.exceptions;

/**
 * Exception for when an invalid argument given to a command
 */
public class InvalidArgumentException extends BobbyException {
    public InvalidArgumentException(String message) {
        super(message);
    }
}
