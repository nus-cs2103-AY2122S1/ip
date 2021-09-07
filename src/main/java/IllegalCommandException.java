package duke.exception;

/**
 * Exception for when an Illegal Command is given.
 */
public class IllegalCommandException extends Exception {
    public IllegalCommandException(String message) {
        super(message);
    }
}