package biscuit.exceptions;

/**
 * Custom Exception thrown for invalid inputs to biscuit.Biscuit
 */
public class BiscuitException extends Exception {
    public BiscuitException(String errorMessage) {
        super(errorMessage);
    }
}