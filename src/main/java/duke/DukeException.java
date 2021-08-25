package duke;

/**
 * Represents exceptions specific to Duke
 *
 * @author Aiken Wong
 */
public class DukeException extends RuntimeException {

    /**
     * Initialises a DukeException instance.
     *
     * @param message
     */
    public DukeException(String message) {
        super(message);
    }
}
