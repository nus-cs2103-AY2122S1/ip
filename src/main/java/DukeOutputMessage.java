/**
 * Encapsulates the base output message that Duke replies to the user.
 * It includes methods to format the output message.
 */
public class DukeOutputMessage {
    private static String FORMATTED_LINE = "_________________________________________________";
    private String message;

    /**
     * Constructor to instantiate a `DukeOutputMessage`.
     *
     * @param message the string to be used in the output message
     */
    public DukeOutputMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the raw message.
     *
     * @return the string message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Gets the message with a face added to it
     *
     * @return the string message with a face
     */
    public String getMessageWithFace() {
        return this.message + " ｢(ﾟﾍﾟ)";
    }

    /**
     * Gets a message formatted with lines, indentation and a face
     *
     * @return the formatted string message
     */
    public String getFormattedMessage() {
        return "\t" + FORMATTED_LINE
                + "\n\t" + this.getMessageWithFace()
                + "\n\t" + FORMATTED_LINE;
    }
}
