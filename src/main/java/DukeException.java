/**
 * Encapsulation of an Exception thrown by Duke's processes.
 */
public class DukeException extends Exception {
    /**
     * Constructor of a DukeException
     *
     * @param message The message sent due to an Exception occurring.
     */
    public DukeException(String message) {
        super(message);
    }
}
