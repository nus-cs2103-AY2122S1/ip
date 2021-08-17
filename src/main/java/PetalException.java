/**
 * The parent PetalException. It represents all possible
 * exceptions in relation to the Petal bot.
 */
public class PetalException extends Exception {

    //Exception message
    private final String message;

    /**
     * Constructor for the PetalException class
     * @param message The message
     */
    public PetalException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Constructor for the PetalException class (Used as Wrapper)
     * @param message The exception message
     * @param cause Initial cause
     */
    public PetalException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

}
