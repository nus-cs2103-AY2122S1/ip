package nyx;

/**
 * Signals that an error has occurred during the execution of Nyx.
 */
public class NyxException extends Exception {
    /**
     * Constructs a NyxException with the specified detail message.
     * @param message The detail message.
     */
    public NyxException(String message) {
        super("☹ OOPS!!! " + message);
    }
}
