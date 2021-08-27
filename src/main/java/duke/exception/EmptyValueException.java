package duke.exception;

/**
 * Handles empty string after keywords.
 */
public class EmptyValueException extends DukeException {
    /**
     * Constructor of EmptyValueException.
     */
    public EmptyValueException() {
        super("Empty value after instruction word.");
    }
}
