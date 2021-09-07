package duke_exception;

/**
 * Represents a done exception.
 */
public class DukeDoneException extends DukeException {
    /**
     * Constructs a DukeDoneException object.
     */
    public DukeDoneException() {
        super("OOPS!!! The index of a done cannot be empty.");
    }
}
