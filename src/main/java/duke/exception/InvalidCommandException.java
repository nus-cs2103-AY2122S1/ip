package duke.exception;

/**
 * This class represents exceptions due to invalid command.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructor to create an invalid command exception.
     *
     * @param msg The error message of the exception.
     */
    public InvalidCommandException(String msg) {
        super(msg);
    }
}