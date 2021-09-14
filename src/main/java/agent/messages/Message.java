package agent.messages;

/**
 * Encapsulates textual data to be output to the user.
 *
 * @author kevin9foong
 */
public class Message {
    private String messageText;

    /**
     * Constructs an instance of <code>Message</code>.
     */
    public Message() {
    }

    /**
     * Constructs an instance of <code>Message</code> with a given <code>String</code>
     * payload to generate response message with.
     *
     * @param messageText main payload to generate response message with.
     */
    public Message(String messageText) {
        this.messageText = messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    /**
     * Returns the user response message with user-friendly separators wrapping around the main
     * message text.
     *
     * @return user-friendly formatted response message.
     */
    @Override
    public String toString() {
        return this.messageText;
    }
}

