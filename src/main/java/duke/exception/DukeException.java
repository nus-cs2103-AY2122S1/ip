package duke.exception;

/**
 * Creates a DukeException.
 *
 * @author: James Kua
 * @version: Duke-Level-8
 */
public class DukeException extends Exception {

    /**
     * Constructor for a DukeException.
     *
     * @param e Message for the error.
     */
    public DukeException(String e) {
        super(e);
    }
}
