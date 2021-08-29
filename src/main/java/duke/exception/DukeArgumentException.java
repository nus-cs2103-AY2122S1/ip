package duke.exception;

/**
 * Represents exceptions related to bad arguments inputted by the user.
 */
public class DukeArgumentException extends Exception {
    /**
     * Creates a new DukeArgumentException
     *
     * @param msg Error message.
     */
    public DukeArgumentException(String msg) {
        super(msg);
    }
}
