/**
 * The parent, PetalException. It represents all possible
 * exceptions in relation to the Petal bot.
 */
public class PetalException extends Exception {

    /**
     * Constructor for the PetalException class
     * @param message The message
     */
    public PetalException(String message) {
        super(message);
    }

    /**
     * Constructor for the PetalException class (Used as Wrapper)
     * @param message The exception message
     * @param cause Initial cause
     */
    public PetalException(String message, Throwable cause) {
        super(message, cause);
    }

}
