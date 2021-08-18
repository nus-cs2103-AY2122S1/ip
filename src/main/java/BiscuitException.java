/**
 * Custom Exception thrown for invalid inputs to Biscuit
 */
public class BiscuitException extends Exception {
    public BiscuitException(String errorMessage) {
        super(errorMessage);
    }
}