package duke.exception;

/**
 * This class encapsulates all exceptions specific to Duke.
 *
 * @author Teo Sin Yee
 * @version CS2103T AY21/22 Semester 1
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     *
     * @param message error message.
     */
    public DukeException(String message) {
        super(message);
    }
}