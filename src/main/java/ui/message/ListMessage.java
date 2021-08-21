package ui.message;

/**
 * Encapsulates a message containing a list of tasks.
 * It inherits from `ui.message.DukeOutputMessage`.
 * It overrides methods in `ui.message.DukeOutputMessage` to format the message differently for a list.
 */
public class ListMessage extends Message {
    private static String LIST_PREFIX = "Here are the tasks in your list:";

    /**
     * Constructor to instantiate a `ui.message.DukeListMessage`.
     * Instantiates a parent `ui.message.DukeOutputMessage`.
     *
     * @param message the string to be used in the list message
     */
    public ListMessage(String message) {
        super(message, "ヽ(°〇°)ﾉ");
    }

    /**
     * Gets the message with an prefix informing that the list contains tasks
     *
     * @return the string message with the prefix
     */
    @Override
    protected String getMessage() {
        return LIST_PREFIX + "\n\t" + super.getMessage();
    }
}
