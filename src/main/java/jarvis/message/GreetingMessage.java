package jarvis.message;

/**
 * Encapsulates an greeting message that is shown when program starts
 */
public class GreetingMessage extends OutputMessage{
    private static final String DEFAULT_GREETING_MESSAGE = String.format(
            "%s\n%s",
            "Hello! I'm Jarvis!",
            "What can I do for you?"
    );

    /**
     * Default constructor for GreetingMessage that contains DEFAULT_GREETING_MESSAGE
     */
    public GreetingMessage() {
        super(GreetingMessage.DEFAULT_GREETING_MESSAGE);
    }

    /**
     * Constructor for GreetingMessage that contains a custom message
     *
     * @param message A custom greeting message
     */
    public GreetingMessage(String message) {
        super(message);
    }
}
