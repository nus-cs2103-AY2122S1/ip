package message;

public class GreetingMessage extends OutputMessage{
    public GreetingMessage() {
        super("Hello! I'm Duke" + OutputMessage.NEW_LINE + OutputMessage.TAB + "What can I do for you?");
    }

    public GreetingMessage(String message) {
        super(message);
    }
}
