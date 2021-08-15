package jarvis.message;

public class ExitMessage extends OutputMessage{
    private static final String defaultExitMessage = "Bye Bye! I am always ready when you need me!";

    public ExitMessage() {
        super(ExitMessage.defaultExitMessage);
    }

    public ExitMessage(String message) {
        super(message);
    }
}
