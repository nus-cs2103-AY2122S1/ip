package duke.exception;

/**
 * This class encapsulates exception due to I/O exception.
 *
 * @author Teo Sin Yee
 */
public class DukeIoException extends DukeException {
    /**
     * Constructor for DukeIoException.
     */
    public DukeIoException(String errorMessage) {
        super(errorMessage);
    }
}
