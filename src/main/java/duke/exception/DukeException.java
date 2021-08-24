package duke.exception;

/**
 * A class that encapsulates all exceptions specific to Duke.
 *
 * @author Teo Sin Yee
 * @version CS2103T AY21/22 Semester 1
 */
public class DukeException extends Exception {
    /**
     * Instantiates a new Duke exception
     *
     * @param message error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
