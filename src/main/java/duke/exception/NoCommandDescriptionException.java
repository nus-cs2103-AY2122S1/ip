package duke.exception;

/**
 * Signals that an error has occurred due to empty description of a command.
 *
 * @author Zhi Bin
 * @version Duke Level 9
 */
public class NoCommandDescriptionException extends DukeException {
    public NoCommandDescriptionException() {
        super();
    }

    /**
     * Returns a formatted error message to tell the
     * user that a command must come with a description.
     *
     * @return The formatted error message.
     */
    @Override
    public String getMessage() {
        return super.formatMessage("Hello??? Your command no description one ah???");
    }
}
