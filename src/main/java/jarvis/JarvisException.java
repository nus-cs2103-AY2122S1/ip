package jarvis;

/**
 * Class that throws exceptions specific to Jarvis
 */
public class JarvisException extends Exception {

    protected String message;

    /**
     * Prints the received error message to user
     *
     * @param message The error message to be printed
     */
    public JarvisException(String message) {
        super(message);
        this.message = message;
        assert !this.message.equals("") : "Error message is empty!";
    }

    /**
     * Returns the error message
     *
     * @return the error message
     */
    @Override
    public String toString() {
        return message;
    }
}
