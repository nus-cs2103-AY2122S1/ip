package tipsy;

/**
 * TipsyException is the class that represents exceptions in the
 * logic of the script components in Tipsy.
 */
public class TipsyException extends Exception {
    /**
     * Class constructor.
     *
     * @param message the message describing the error that occurred.
     */
    public TipsyException(String message) {
        super(message);
    }
}
