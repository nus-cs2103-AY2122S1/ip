package message;

public class ExitMessage extends OutputMessage{
    public ExitMessage() {
        super("Bye. Hope to see you again soon!");
    }

    public ExitMessage(String message) {
        super(message);
    }
}
