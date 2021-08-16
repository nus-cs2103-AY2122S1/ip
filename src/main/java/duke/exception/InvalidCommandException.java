package duke.exception;

/**
 * Thrown when a command is not registered under the commands registry or does
 * not exists.
 */
public class InvalidCommandException extends DukeException {

    public InvalidCommandException(String message) {
        super(message);
    }

}
