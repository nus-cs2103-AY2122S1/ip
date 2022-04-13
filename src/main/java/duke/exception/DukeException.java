package duke.exception;

/**
 * Main exception class for the bot.
 */
public class DukeException extends RuntimeException {

    /**
     * Public constructor for the exception which is handled by the bot.
     *
     * @param message The error message to accompany the error thrown.
     */
    public DukeException(String message) {
        super(message);
    }
}
