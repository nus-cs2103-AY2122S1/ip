package duke_exception;

/**
 * Represents an unknown exception.
 */
public class DukeUnknownException extends DukeException {
    /**
     * Constructs a DukeUnknownException object.
     */
    public DukeUnknownException() {
        super("OOPS!!! I'm sorry, but I don't know what that means.");
    }
}
