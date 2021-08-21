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
        super(message);
    }

    /**
     * Gets the message with an prefix informing that the list contains tasks
     *
     * @return the string message with the prefix
     */
    @Override
    public String getMessage() {
        return LIST_PREFIX + "\n\t" + super.getMessage();
    }

    /**
     * Gets the message with a shocked face added to it
     *
     * @return the string message with a shocked face
     */
    @Override
    public String getMessageWithFace() {
        return this.getMessage() + "ヽ(°〇°)ﾉ";
    }
}
