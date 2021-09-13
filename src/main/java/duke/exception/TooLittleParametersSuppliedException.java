package duke.exception;

public class TooLittleParametersSuppliedException extends IncorrectParameterSuppliedException {
    /**
     * The constructor method for DukeException.
     */
    public TooLittleParametersSuppliedException() {
        super("task", new String[]{"adding more parameters to specify the task."});
    }
}
