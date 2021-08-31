package lifeline.exception;

/**
 * The class LifelineException is a custom exception class that represents exceptions specific to Lifeline.
 */
public class LifelineException extends Exception {

    /**
     * Default constructor for a LifelineException.
     *
     * @param errorMessage Error message to be displayed to user.
     */
    public LifelineException(String errorMessage) {
        super(errorMessage);
    }
}
