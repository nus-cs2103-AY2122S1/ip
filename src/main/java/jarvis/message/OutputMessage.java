package jarvis.message;

/**
 * Encapsulates messages that is shown to user.
 */
public class OutputMessage {
    private String message;

    /**
     * Constructor for OutputMessage that contains a custom message.
     *
     * @param message A custom message.
     */
    public OutputMessage(String message) {
        assert !message.equals("") : "Output message is empty";
        this.message = message;
    }

    /**
     * Returns the message without any formatting.
     *
     * @return Unformatted message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the message with the message sandwiched between two new lines.
     *
     * @return Formatted message.
     */
    public String getFormattedMessage() {
        return String.format("%s\n", this.getMessage());
    }
}

