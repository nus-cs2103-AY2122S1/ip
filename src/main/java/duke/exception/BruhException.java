package duke.exception;

/**
 * Represents a base class for all exceptions specific to the Bruh chatbot.
 */
public class BruhException extends Exception {
    /**
     * Constructor for an exception specific to the Bruh chatbot.
     *
     * @param message The exception message.
     */
    public BruhException(String message) {
        super(message);
    }
}
