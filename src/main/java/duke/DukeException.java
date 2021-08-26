package duke;

/**
 * All errors related to Duke can be changed to DukeException.
 */
public class DukeException extends Exception {
    /**
     * Constructor of Duke.
     *
     * @param s Error message.
     */
    public DukeException(String s) {
        super(s);
    }
}