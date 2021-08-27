package ui.message;

/**
 * Encapsulates a message containing a list of tasks.
 */
public class ListMessage extends Message {
    private String prefix;

    /**
     * Instantiates a message describing a list.
     *
     * @param prefix
     * @param message
     */
    public ListMessage(String prefix, String message) {
        super(message, "ヽ(°〇°)ﾉ");
        this.prefix = prefix;
    }

    /**
     * Gets the message with an prefix informing that the list contains tasks.
     *
     * @return String message with the prefix.
     */
    @Override
    protected String getMessage() {
        return prefix + "\n\t" + super.getMessage();
    }
}
