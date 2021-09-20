package biscuit.exceptions;

/**
 * Custom Exception thrown for invalid inputs to Biscuit.
 */
public class BiscuitException extends Exception {
    /**
     * Constructs BiscuitException class.
     *
     * @param errorMessage Error message to display.
     */
    public BiscuitException(String errorMessage) {
        super(errorMessage);
    }
}
