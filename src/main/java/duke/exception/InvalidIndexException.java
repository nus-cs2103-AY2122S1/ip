package duke.exception;

/**
 * Represents InvalidIndexException where an invalid index is provided by user.
 *
 * @author Sherman Ng Wei Sheng
 */
public class InvalidIndexException extends DukeException {
    public InvalidIndexException() {
        super("OOPS!!! Kindly key in a valid index!");
    }
}
