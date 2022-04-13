package bot.error;

import static bot.constants.GlobalStringFormats.LINE_BREAK;

/**
 * Represents the unique DukeException.
 */
public class DukeException extends Exception {

    /**
     * Creates a DukeException with a specified message.
     *
     * @param message The message of this DukeException.
     */
    public DukeException(String message) {
        super(LINE_BREAK + message);
    }
}
