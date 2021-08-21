package entity.message;

/**
 * Encapsulates the base output message that Duke replies to the user.
 * It includes methods to format the output message.
 */
public class Message {
    private static String FORMATTED_LINE = "_________________________________________________";
    private String message;

    /**
     * Constructor to instantiate a `entity.message.DukeOutputMessage`.
     *
     * @param message the string to be used in the output message
     */
    public Message(String message) {
        this.message = message;
    }

    protected String getMessage() {
        return this.message;
    }

    protected String getMessageWithFace() {
        return this.message + " ｢(ﾟﾍﾟ)";
    }

    private String getFormattedMessage() {
        return "\t" + FORMATTED_LINE
                + "\n\t" + this.getMessageWithFace()
                + "\n\t" + FORMATTED_LINE;
    }

    public void print() {
        System.out.println(this.getFormattedMessage());
    }
}
