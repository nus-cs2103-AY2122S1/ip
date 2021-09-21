package duke.exception;

/**
 * The class that models Exceptions to be thrown by Duke.
 */
public class DukeException extends Exception {

    /**
     * Instantiates a DukeException with given error message.
     * @param msg
     */
    public DukeException(String msg) {
        super(msg);
    }
}
