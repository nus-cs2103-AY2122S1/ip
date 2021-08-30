package kermit;

/**
 * Exception thrown whenever there is an error in Kermit.
 */
public class KermitException extends Exception {
    /**
     * Constuctor for KermitException.
     *
     * @param message The reason why Kermit encountered an error.
     */
    public KermitException(String message) {
        super("☹ BURP-ribbit ribbit. " + message);
    }
}
