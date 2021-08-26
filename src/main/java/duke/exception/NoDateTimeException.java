package duke.exception;

/**
 * Signals that an error has occurred due to empty
 * datetime description of a deadline or event task.
 *
 * @author Zhi Bin
 * @version Duke Level 9
 */
public class NoDateTimeException extends DukeException {
    public NoDateTimeException() {
        super();
    }

    /**
     * Returns a formatted error message to tell the
     * user that a deadline or event task must come
     * with a datetime.
     *
     * @return The formatted error message.
     */
    @Override
    public String getMessage() {
        return super.formatMessage("Hello??? Your task no date and time one ah???");
    }
}
