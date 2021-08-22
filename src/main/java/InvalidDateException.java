/**
 * Encapsulates the InvalidDateException which is a DukeException thrown by the Duke bot.
 *
 * @author Dickson
 */
public class InvalidDateException extends DukeException {
    /**
     * Constructor for InvalidInputException.
     *
     * @param message message to show user for InvalidDataException.
     */
    public InvalidDateException(String message) {
        super(message);
    }

    /**
     * Gets string message of thrown InvalidDataException.
     *
     * @return String representation of thrown DukeException.
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
