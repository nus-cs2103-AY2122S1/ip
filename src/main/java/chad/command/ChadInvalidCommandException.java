package chad.command;

/**
 * Represents an exception that occurs when an invalid command is used as an input for the Chad chatbot.
 *
 * @author Jay Aljelo Saez Ting
 */
public class ChadInvalidCommandException extends IllegalArgumentException {

    /**
     * Constructs a ChadInvalidCommandException instance with the given error message.
     *
     * @param message The error message.
     */
    public ChadInvalidCommandException(String message) {
        super(message);
    }
}
