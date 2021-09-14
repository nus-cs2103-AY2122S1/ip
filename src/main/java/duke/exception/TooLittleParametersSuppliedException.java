package duke.exception;

/**
 * An exception to indicate the incorrect number of Todo Task parameters provided.
 */
public class TooLittleParametersSuppliedException extends IncorrectParameterSuppliedException {
    /**
     * The constructor method for DukeException.
     */
    public TooLittleParametersSuppliedException() {
        super("task", new String[]{"adding more parameters to specify the task."});
    }
}
