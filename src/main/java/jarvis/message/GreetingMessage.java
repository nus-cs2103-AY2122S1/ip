package jarvis.message;

public class GreetingMessage extends OutputMessage{
    private static final String defaultGreetingMessage = String.format(
            "%s\n%s",
            "Hello! I'm Jarvis!",
            "What can I do for you?"
    );

    public GreetingMessage() {
        super(GreetingMessage.defaultGreetingMessage);
    }

    public GreetingMessage(String message) {
        super(message);
    }
}
