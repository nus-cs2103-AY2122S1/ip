package duke.exception;

/**
 * All errors related to Duke can be changed to DukeException.
 */
public class DukeException extends Exception {
    /**
     * Constructor of DukeException.
     *
     * @param s Error message.
     */
    public DukeException(String s) {
        super(s);
    }
}
