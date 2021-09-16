package sun.data.exception;

/**
 * Class that signals an error that Sun may encounter.
 *
 * @author Won Ye Ji
 */
public class SunException extends Exception {

    /**
     * Constructor for SunException.
     *
     * @param message should contain relevant information on the failed execution(s).
     */
    public SunException(String message) {
        super(message);
    }
}
