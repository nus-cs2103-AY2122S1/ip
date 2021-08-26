package eightbit;

/**
 * Exception that is used to handle incorrect inputs from user.
 */
public class EightBitException extends Exception {
    private String errorMessage;

    /**
     * Constructor.
     *
     * @param errorMessage Error message to be shown to user.
     */
    public EightBitException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return String representation of the error message.
     */
    @Override
    public String toString() {
        return this.errorMessage;
    }
}
