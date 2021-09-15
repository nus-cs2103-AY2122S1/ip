package angrybot.exception;

/**
 * Signals that an error has occurred due to empty
 * datetime description of a deadline or event task.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public class NoDateTimeException extends AngryBotException {
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
        return "Hello??? Your task no date and time one ah???";
    }
}
