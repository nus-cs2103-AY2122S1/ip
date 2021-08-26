package duke.exception;

/**
 * Represents an exception thrown when command is in incorrect format.
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("Nee doesn't understand your command!");
    }
}
