package duke.exception;

/**
 * Exceptions thrown by the bot.
 */
public class DukeException extends Exception {

    /**
     * Constructs the exception.
     *
     * @param message The error message shown to the user.
     */
    public DukeException(String message) {
        super(message);
    }

}
