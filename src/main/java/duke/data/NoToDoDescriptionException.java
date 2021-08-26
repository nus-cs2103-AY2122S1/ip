package duke.data;

/**
 * Signals that an error has occurred due to empty description of a todo task.
 *
 * @author Zhi Bin
 * @version Duke Level 8
 */
public class NoToDoDescriptionException extends DukeException {
    public NoToDoDescriptionException() {
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
        return super.formatMessage("Hello??? What you want me to do???");
    }
}
