package duke.command;

/**
 * Signals a user command that is not supported.
 */
public class UnsupportedCommandException extends Exception {
    public UnsupportedCommandException(String message) {
        super(message);
    }
}
