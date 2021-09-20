package duke.exception;

/**
 * Represents IllegalFormatException where formatting errors are detected from the user input.
 *
 * @author Sherman Ng Wei Sheng
 */
public class IllegalFormatException extends DukeException {
    public IllegalFormatException(String message) {
        super(message);
    }
}
