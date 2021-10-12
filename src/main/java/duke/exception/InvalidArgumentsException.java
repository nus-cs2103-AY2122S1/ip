package duke.exception;

/**
 * A DukeException that occurs when a command
 * contains invalid or missing arguments.
 */
public class InvalidArgumentsException extends DukeException {

    /**
     * Public constructor to create an InvalidArgumentsException.
     */
    public InvalidArgumentsException() {
        super("Invalid or Missing Arguments");
    }
}
