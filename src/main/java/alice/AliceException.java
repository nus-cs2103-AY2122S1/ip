package alice;

/**
 * Exception to do with Alice to be thrown when the user interact incorrectly
 * with the personal assistant
 */
public class AliceException extends RuntimeException {
    /**
     * Default constructor of AliceException
     *
     * @param errorMessage message to be stored in the AliceException
     */
    public AliceException(String errorMessage) {
        super(errorMessage);
    }
}
