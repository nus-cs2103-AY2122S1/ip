package exception;

/**
 * Exception class for valid commands but invalid inputs.
 */
public class InvalidInputException extends DukeException {
    /**
     * Constructor of the InvalidInputException.
	 *
	 * @param error The error message.
	 */
    public InvalidInputException(String error) {
        super("[Invalid Input] " + error);
    }
}
