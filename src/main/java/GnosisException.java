/**
 * This exception Class presents exception captured by Gnosis.
 *
 *
 * @author Pawandeep Singh
 * @version Level-5
 *
 * */
public class GnosisException extends Exception {
    private final String ERROR_MESSAGE;

    public GnosisException(String errorMessage) {
        super(errorMessage);
        this.ERROR_MESSAGE = errorMessage;
    }

    @Override
    public String toString() {
        return "~~" + this.ERROR_MESSAGE + "~~\nPLEASE TRY AGAIN:";
    }
}
