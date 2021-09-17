package duke.exception;

/**
 * This class encapsulates exception due to invalid task index.
 *
 * @author Teo Sin Yee
 */
public class DukeSortException extends DukeException {
    /**
     * Constructor for DukeSortException.
     */
    public DukeSortException() {
        super("Error: Invalid use of 'sort' command! Enter 'sort reverse' if you want to reverse order of list.");
    }
}
