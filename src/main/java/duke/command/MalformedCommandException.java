package duke.command;

/**
 * Signals a user command that was incorrectly formatted.
 */
public class MalformedCommandException extends Exception {
    public MalformedCommandException(String message) {
        super(message);
    }
}
