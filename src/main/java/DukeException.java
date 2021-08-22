/**
 * Encapsulates the exceptions thrown by the Duke bot.
 *
 * @author Dickson
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     *
     * @param message
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Gets string message of thrown DukeException.
     *
     * @return String representation of thrown DukeException.
     */
    @Override
    public String getMessage() {
        return "☹ OOPS!!! " + super.getMessage();
    }
}
