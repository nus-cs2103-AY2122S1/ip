package duke;

/**
 * DukeException is an exception thrown by Duke's actions.
 */
public class DukeException extends Exception {
    /**
     * Initialises the exception with an errorMessage.
     *
     * @param errorMessage the errorMessage to be displayed.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
