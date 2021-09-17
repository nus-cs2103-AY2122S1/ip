package duke.exception;

/**
 * Exception representing an error with the input to a command.
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String message) {
        super(message);
    }
}
