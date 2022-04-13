package petal.exception;

/**
 * The parent, PetalException. It represents all possible
 * exceptions in relation to the Petal bot.
 */
public class PetalException extends Exception {

    /**
     * Constructs a PetalException
     *
     * @param message The message
     */
    public PetalException(String message) {
        super(message);
    }

    /**
     * Constructs a PetalException (used as a wrapper)
     *
     * @param message The exception message
     * @param cause Initial cause
     */
    public PetalException(String message, Throwable cause) {
        super(message, cause);
    }

}
