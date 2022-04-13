package duke.exception;

/**
 * Exception of Duke that occurs when an unknown command is given.
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super("Sorry, I don't understand that command.");
    }
}
