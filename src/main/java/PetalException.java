/**
 * The parent PetalException. It represents all possible
 * exceptions in relation to the Petal bot.
 */
public class PetalException extends Exception {

    //Exception message
    private String message;

    /**
     * Constructor for the PetalException class
     * @param message The message
     */
    public PetalException(String message) {
        super(message);
        this.message = message;
    }

}
