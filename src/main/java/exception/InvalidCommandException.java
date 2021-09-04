package exception;

/**
 * The InvalidCommandException Exception is thrown when an input is not part of the feature set.
 */
public class InvalidCommandException extends DukeException {

    /**
     * Constructs a new InvalidCommandException.
     */
    public InvalidCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
