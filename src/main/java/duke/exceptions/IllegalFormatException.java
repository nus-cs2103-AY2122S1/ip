package duke.exceptions;

/**
 * Encapsulates the IllegalFormatException thrown by the Duke bot.
 */
public class IllegalFormatException extends DukeException {
    /**
     * Constructor for IllegalFormatException.
     */
    public IllegalFormatException(String format) {
        super("☹ Sorry! Please follow the following input format:\n" + format);
    }
}
