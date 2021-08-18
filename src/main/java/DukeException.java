/**
 * Exceptions that can occur specifically in the Duke program.
 */
public class DukeException extends Exception {
    /**
     * Constructor of the DukeException
     * @param message Message to be printed out to user when error has occured.
     */
    public DukeException(String message) {
        super(message);
    }
}
