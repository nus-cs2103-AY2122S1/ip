package message;

public class GreetingMessage extends OutputMessage{
    private static final String defaultGreetingMessage = "Hello! I'm JARVIS"
            + OutputMessage.NEW_LINE
            + OutputMessage.TAB
            + "What can I do for you?";

    public GreetingMessage() {
        super(GreetingMessage.defaultGreetingMessage);
    }

    public GreetingMessage(String message) {
        super(message);
    }
}
