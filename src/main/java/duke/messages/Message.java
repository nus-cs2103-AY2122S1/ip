package duke.messages;

/**
 * Encapsulates textual data to be output to the user.
 *
 * @author kevin9foong
 */
public class Message {
    private String messageText;
    private final String separator =
            "____________________________________________________________";

    public Message() {
    }

    public Message(String messageText) {
        this.messageText = messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    @Override
    public String toString() {
        return separator + "\n" + this.messageText + "\n" + separator;
    }
}

