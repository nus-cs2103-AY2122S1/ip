/**
 * Encapsulates the InvalidInputException which is a DukeException thrown by the Duke bot.
 *
 * @author Dickson
 */
public class InvalidInputException extends DukeException {
    /**
     * Constructor for InvalidInputException.
     *
     * @param message
     */
    public InvalidInputException(String message) {
        super(message);
    }

    /**
     * Gets string message of thrown InvalidInputException.
     *
     * @return String representation of thrown DukeException.
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
