/**
 * A class to create invalid type exceptions.
 *
 * @author Felissa Faustine
 */
public class InvalidTypeException extends RuntimeException {

    /**
     * The constructor for the InvalidTypeExceptions.
     *
     * @param message The error message.
     */
    public InvalidTypeException(String message) {
        super(message);
    }

    /**
     * Gets the string representation of the exception.
     *
     * @return The string representation of the exception.
     */
    @Override
    public String toString() {
        return "InvalidTypeException: " + getMessage();
    }
}
