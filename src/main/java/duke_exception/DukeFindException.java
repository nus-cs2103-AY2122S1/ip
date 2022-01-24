package duke_exception;

/**
 * Represents a find exception.
 */
public class DukeFindException extends DukeException{
    /**
     * Constructs a DukeFindException object.
     */
    public DukeFindException() {
        super("OOPS!!! The query of a find cannot be empty.");
    }
}
