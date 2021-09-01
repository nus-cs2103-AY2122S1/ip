package eightbit;

/**
 * Exception that is used to handle incorrect inputs from user.
 */
public class EightBitException extends Exception {

    /**
     * Constructs the exception with the error message.
     *
     * @param errorMessage Error message to be shown to user.
     */
    public EightBitException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * @return String representation of the error message.
     */
    @Override
    public String toString() {
        return this.getMessage();
    }
}
