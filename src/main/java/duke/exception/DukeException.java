package duke.exception;

/**
 * Represents DukeException that all other Exceptions related to this program inherits from.
 *
 * @author Sherman Ng Wei Sheng
 */
public class DukeException extends Exception {
    private static final String HELP_MESSAGE = " Type 'help' for more information!";

    public DukeException(String message) {
        super(message + HELP_MESSAGE);
    }
}
