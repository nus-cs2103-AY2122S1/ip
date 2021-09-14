package duke.exception;

/**
 * Represents DukeException that all other Exceptions related to this program inherits from.
 *
 * @author Sherman Ng Wei Sheng
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
