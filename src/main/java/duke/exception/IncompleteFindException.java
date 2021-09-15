package duke.exception;

/**
 * Representation for incomplete keyword for FindCommand exception.
 */
public class IncompleteFindException extends DukeException {
    private static String MESSAGE = "OOPS!!! Please remember to key in keyword to find!";

    /**
     * Constructor for IncompleteFindException.
     */
    public IncompleteFindException() {
        super(MESSAGE);
    }
}
