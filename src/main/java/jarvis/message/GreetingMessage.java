package jarvis.message;


/**
 * Encapsulates a greeting message that is shown when program starts.
 */
public class GreetingMessage extends OutputMessage {
    private static final String DEFAULT_GREETING_MESSAGE = String.format(
            "%s\n%s\n%s\n\n%s",
            "Starting Jarvis Neural Network...",
            "Downloading Start Industries Database...",
            "Jarvis up and running...",
            "Hello, I'm Jarvis! How can I help you?"
    );

    /**
     * Constructor for GreetingMessage that contains DEFAULT_GREETING_MESSAGE.
     */
    public GreetingMessage() {
        super(GreetingMessage.DEFAULT_GREETING_MESSAGE);
    }

    /**
     * Constructor for GreetingMessage that contains a custom message.
     *
     * @param message A custom greeting message.
     */
    public GreetingMessage(String message) {
        super(message);
    }
}
