/**
 * Exception that is used to handle incorrect inputs from user.
 */
public class EightBitException extends Exception {
    private String errorMessage;

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
