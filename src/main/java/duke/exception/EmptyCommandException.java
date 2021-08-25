package duke.exception;

/**
 * Exception of Duke that occurs when no command is inputted by user.
 */
public class EmptyCommandException extends DukeException {
    public EmptyCommandException() {
        super("You left the field blank!");
    }
}
