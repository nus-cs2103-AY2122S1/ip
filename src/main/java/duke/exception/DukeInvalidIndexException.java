package duke.exception;

/**
 * This class encapsulates exception due to invalid index.
 *
 * @author Teo Sin Yee
 */
public class DukeInvalidIndexException extends DukeException {
    /**
     * Constructor for DukeInvalidIndexException.
     */
    public DukeInvalidIndexException() {
        super("Error: Please enter a positive number starting from 1!");
    }
}