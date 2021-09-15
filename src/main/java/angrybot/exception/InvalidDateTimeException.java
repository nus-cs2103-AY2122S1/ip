package angrybot.exception;

/**
 * Signals that an error has occurred while trying
 * to create a task with date and time. The datetime
 * format entered by the user is not supported by
 * the program.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public class InvalidDateTimeException extends AngryBotException {
    public InvalidDateTimeException() {
        super();
    }

    /**
     * Returns a formatted error message to indicate to the
     * user that the inputted datetime format is not supported.
     * The message also included the format that the program supports.
     *
     * @return The formatted error message.
     */
    @Override
    public String getMessage() {
        return "Entered DateTime format not supported. "
                + "Please enter Date and Time in YYYY-MM-DD HH:MM.";
    }
}
