package jarvis.exception;

/**
 * Encapsulates an exception when the user input is invalid.
 */
public class InvalidInputException extends JarvisException {
    /**
     * Constructor for InvalidInputException.
     *
     * @param inputType The type of input required (e.g. number).
     */
    public InvalidInputException(String inputType) {
        super(String.format("Please enter a %s!", inputType));
    }
}
