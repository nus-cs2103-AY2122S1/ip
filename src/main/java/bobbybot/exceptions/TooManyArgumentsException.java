package bobbybot.exceptions;

/**
 * Exception for when too many arguments are given to a command
 */
public class TooManyArgumentsException extends BobbyException {
    public TooManyArgumentsException(String message) {
        super(message);
    }
}
