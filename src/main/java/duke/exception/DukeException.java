package duke.exception;

/**
 * Encapsulates all exceptions that can occur in Duke bot.
 */
public class DukeException extends IllegalArgumentException {

    public DukeException(String message) {
        super(message);
    }

}
