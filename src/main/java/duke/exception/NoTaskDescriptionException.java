package duke.exception;

/**
 * Signals that an error has occurred due to empty description of a task.
 *
 * @author Zhi Bin
 * @version Duke Level 9
 */
public class NoTaskDescriptionException extends DukeException {
    public NoTaskDescriptionException() {
        super();
    }

    /**
     * Returns a formatted error message to tell the
     * user that a todo task must come with a description.
     *
     * @return The formatted error message.
     */
    @Override
    public String getMessage() {
        return super.formatMessage("Hello??? Your task no description one ah???");
    }
}
