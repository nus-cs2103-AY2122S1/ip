package duke;

/**
 * Represents a DukeException, which is a subtype of Exception.
 * Encapsulates numerous errors which could occur during runtime
 * due to invalid user input.
 *
 * @author Joshua Yong
 */
public class DukeException extends Exception {

    /**
     * Class constructor.
     *
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }

}
