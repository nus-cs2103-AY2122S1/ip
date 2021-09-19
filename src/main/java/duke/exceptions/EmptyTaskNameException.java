package duke.exceptions;

/**
 * Signals that there is an empty task name error.
 */
public class EmptyTaskNameException extends DukeException {
    /**
     * Initialises a EmptyTaskNameException object.
     *
     * @param errorMessage contains the error message when there is an empty task name
     */
    public EmptyTaskNameException(String errorMessage) {
        super(errorMessage);
    }
}
