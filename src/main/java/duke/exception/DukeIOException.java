package duke.exception;

/**
 * This class encapsulates exception due to I/O exception.
 *
 * @author Teo Sin Yee
 */
public class DukeIOException extends DukeException {
    public DukeIOException(String errorMessage) {
        super(errorMessage);
    }
}