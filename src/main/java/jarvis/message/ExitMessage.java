package jarvis.message;

/**
 * Encapsulates an exit message that is shown when program exits.
 */
public class ExitMessage extends OutputMessage {
    private static final String DEFAULT_EXIT_MESSAGE = "Bye Bye! I am always ready when you need me!";

    /**
     * Default constructor for ExitMessage that contains DEFAULT_EXIT_MESSAGE.
     */
    public ExitMessage() {
        super(ExitMessage.DEFAULT_EXIT_MESSAGE);
    }

    /**
     * Constructor for ExitMessage that contains a custom message.
     *
     * @param message A custom exit message.
     */
    public ExitMessage(String message) {
        super(message);
    }
}
