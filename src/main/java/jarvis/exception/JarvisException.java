package jarvis.exception;

/**
 * The main exception for Jarvis.
 */
public class JarvisException extends Exception {
    /**
     * Constructor for JarvisException.
     *
     * @param errorMessage The error message that should be shown to user.
     */
    public JarvisException(String errorMessage) {
        super(errorMessage);
    }
}
