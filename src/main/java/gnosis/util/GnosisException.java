package gnosis.util;

/**
 * This exception Class presents exception captured by Gnosis.
 *
 * @author Pawandeep Singh
 * */
public class GnosisException extends Exception {

    private String errorMessage;

    /**
     * GnosisException constructor to initialise exception.
     *
     * @param errorMessage Error message to display
     */
    public GnosisException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Retrieves string representation of error message.
     *
     * @return String representative error message.
     */
    @Override
    public String toString() {
        return "~~" + this.errorMessage + "~~\nPLEASE TRY AGAIN:";
    }
}
