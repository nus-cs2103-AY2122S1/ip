package jarvis.message;

public class ExitMessage extends OutputMessage{
    private static final String DEFAULT_EXIT_MESSAGE = "Bye Bye! I am always ready when you need me!";

    public ExitMessage() {
        super(ExitMessage.DEFAULT_EXIT_MESSAGE);
    }

    public ExitMessage(String message) {
        super(message);
    }
}
