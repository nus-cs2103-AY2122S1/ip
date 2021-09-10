package duke.exception;

public class TooLittleParametersSuppliedException extends DukeException {
    /**
     * The constructor method for DukeException.
     */
    public TooLittleParametersSuppliedException() {
        super("Too little parameters were supplied. Please add other words after the Command Word.");
    }
}
