package duke.exceptions;

/**
 * Encapsulates the IllegalFormatException thrown by the Duke bot.
 */
public class IllegalFormatException extends DukeException {
    /**
     * Constructor for IllegalFormatException.
     *
     * @param format correct format for user to follow.
     */
    public IllegalFormatException(String format) {
        super("Sorry! Please follow the following input format:\n" + format);
    }
}
