package messages;

public class Message {
    private final String messageText;
    private final String separator =
            "____________________________________________________________";

    public Message(String messageText) {
        this.messageText = messageText;
    }

    @Override
    public String toString() {
        return separator + "\n" + this.messageText + "\n" + separator;
    }
}

