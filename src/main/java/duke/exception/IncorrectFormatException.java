package duke.exception;

/**
 * Exception of Duke that occurs when an incorrect format is used for event or deadline.
 */
public class IncorrectFormatException extends DukeException {

    /**
     * Constructor of IncorrectFormatException.
     *
     * @param command event or deadline depending on what user called.
     * @param missingWord "/at" or "/by" depending on if user called event or deadline.
     */
    public IncorrectFormatException(String command, String missingWord) {
        super("Please use " + missingWord + " when you want to call the "
                + command
                + " command and include the date and time as YYYY/MM/DD and HHMM in 24 hour format.");
    }
}
