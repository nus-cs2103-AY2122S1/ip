package duke.exception;

/**
 * This class encapsulates exception due to missing index.
 *
 * @author Teo Sin Yee
 */
public class DukeMissingIndexException extends DukeException {
    /**
     * Constructor for DukeMissingIndexException.
     */
    public DukeMissingIndexException(String taskType) {
        super(String.format("Error: The index argument for '%s' cannot be empty. x_x", taskType));
    }
}