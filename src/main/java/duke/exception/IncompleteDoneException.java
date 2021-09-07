package duke.exception;

/**
 * Representation for incomplete fields of Done exception.
 */
public class IncompleteDoneException extends DukeException {
    private static String MESSAGE = "Please key in valid number to mark as done.";

    /**
     * Constructor for IncompleteDoneException.
     */
    public IncompleteDoneException() {
        super(MESSAGE);
    }
}
