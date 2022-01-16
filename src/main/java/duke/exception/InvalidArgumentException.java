package duke.exception;

/**
 * Thrown when the argument for a command is absent/invalid.
 */
public class InvalidArgumentException extends DukeException {

    public InvalidArgumentException(String message) {
        super(message);
    }

}
