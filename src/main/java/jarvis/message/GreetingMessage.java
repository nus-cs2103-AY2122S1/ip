package jarvis.message;

public class GreetingMessage extends OutputMessage{
    private static final String DEFAULT_GREETING_MESSAGE = String.format(
            "%s\n%s",
            "Hello! I'm Jarvis!",
            "What can I do for you?"
    );

    public GreetingMessage() {
        super(GreetingMessage.DEFAULT_GREETING_MESSAGE);
    }

    public GreetingMessage(String message) {
        super(message);
    }
}
