package dac.exception;

/**
 * Represents an invalid input. Thrown when user input is invalid.
 */
public class InvalidInputException extends DukeException {

    private String err;

    /**
     * Constructor.
     *
     * @param str The invalid input.
     */
    public InvalidInputException(String str) {
        super(str);
        this.err = str;
    }

    /**
     * Returns a String representation of the exception.
     *
     * @return String representation of the exception.
     */
    @Override
    public String toString() {
        return "Invalid input: " + err;
    }
}
