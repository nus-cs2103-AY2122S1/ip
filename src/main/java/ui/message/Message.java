package ui.message;

/**
 * Encapsulates the base output message that Duke replies to the user.
 * It includes methods to format the output message.
 */
public class Message {
    private static final String FORMATTED_LINE = "_________________________________________________";
    private final String message;
    private final String face;

    /**
     * Instantiates a message to be returned to the user.
     *
     * @param message Message content.
     * @param face Kaomoji.
     */
    public Message(String message, String face) {
        this.message = message;
        this.face = face;
    }

    /**
     * Prints the formatted message for the user to see.
     */
    public void print() {
        System.out.println(this.getFormattedMessage());
    }

    protected String getMessage() {
        return this.message;
    }

    private String getMessageWithFace() {
        char lastCharacter = this.getMessage().charAt(this.getMessage().length() - 1);

        if (Character.isWhitespace(lastCharacter)) {
            return String.format("%s%s", this.getMessage(), this.face);
        }
        return String.format("%s %s", this.getMessage(), this.face);
    }

    private String getFormattedMessage() {
        return "\t" + FORMATTED_LINE
                + "\n\t" + this.getMessageWithFace()
                + "\n\t" + FORMATTED_LINE;
    }
}
