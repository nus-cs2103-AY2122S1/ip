package ui.message;

/**
 * Encapsulates a message containing a list of tasks.
 */
public class ListMessage extends Message {
    private static String LIST_PREFIX = "Here are the tasks in your list:";

    public ListMessage(String message) {
        super(message, "ヽ(°〇°)ﾉ");
    }

    /**
     * Gets the message with an prefix informing that the list contains tasks.
     *
     * @return String message with the prefix.
     */
    @Override
    protected String getMessage() {
        return LIST_PREFIX + "\n\t" + super.getMessage();
    }
}
