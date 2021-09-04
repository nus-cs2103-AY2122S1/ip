package duke.data.exceptions;

/**
 * Signals that there is an empty task name error.
 */
public class EmptyTaskNameException extends DukeException {
    /**
     * Initialises a EmptyTaskNameException object.
     *
     * @param errorMessage
     */
    public EmptyTaskNameException(String errorMessage) {
        super(errorMessage);
    }
}
