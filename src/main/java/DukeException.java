/**
 * The DukeException class encapsulates exceptions specific to Duke.
 */
public class DukeException extends IllegalArgumentException {
    /**
     * Constructor for DukeException.
     * @param message The message to be shown should there be a Duke Exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns the message regarding the DukeException.
     * @return Message regarding the DukeException.
     */
    @Override
    public String toString() {
        return getMessage();
    }
}
