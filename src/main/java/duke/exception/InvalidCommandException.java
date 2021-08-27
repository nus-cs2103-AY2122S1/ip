package duke.exception;

/**
 * Deals with commands that do not follow format.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructor of EmptyValueException.
     */
    public InvalidCommandException() {
        super("Invalid command/format.");
    }
}
