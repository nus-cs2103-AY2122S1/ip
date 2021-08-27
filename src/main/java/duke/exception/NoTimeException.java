package duke.exception;

/**
 * Deals with commands that do not follow format.
 */
public class NoTimeException extends DukeException {
    /**
     * Constructor of EmptyValueException.
     */
    public NoTimeException() {
        super("You need to input a time with format yyyy-MM-dd.");
    }
}
