package duke;

/**
 * Class that represents exceptions specific to Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor for a DukeException.
     *
     * @param msg Error message to be displayed to the user.
     */
    public DukeException(String msg) {
        super(msg);
    }
}
