package gnosis;

/**
 * This exception Class presents exception captured by Gnosis.
 *
 * @author Pawandeep Singh
 * */
public class GnosisException extends Exception {
    private final String ERROR_MESSAGE;

    /**
     * GnosisException constructor to initialise exception.
     *
     * @param errorMessage Error message to display
     */
    public GnosisException(String errorMessage) {
        super(errorMessage);
        this.ERROR_MESSAGE = errorMessage;
    }

    /**
     * Retrieves string representation of error message.
     *
     * @return String representative error message.
     */
    @Override
    public String toString() {
        return "~~" + this.ERROR_MESSAGE + "~~\nPLEASE TRY AGAIN:";
    }
}
