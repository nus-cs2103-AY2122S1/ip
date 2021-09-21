package lania.exception;

/**
 * Represents special type of exception by the Lania class to handle invalid inputs.
 */
public class LaniaException extends Exception {

    /**
     * Constructor for the LaniaException class.
     *
     * @param message The error message.
     */
    public LaniaException(String message) {
        super(message);
    }

    /**
     * A string representation of the exception.
     *
     * @return The string representation of the exception.
     */
    @Override
    public String toString() {
        return "Oh no!! " + getMessage();
    }
}
