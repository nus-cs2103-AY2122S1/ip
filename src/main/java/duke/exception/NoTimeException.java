package duke.exception;

/**
 * Deals with adding certain tasks without time.
 */
public class NoTimeException extends DukeException {
    /**
     * Constructor of EmptyValueException.
     */
    public NoTimeException() {
        super("You need to input a time with format yyyy-MM-dd.");
    }
}
