/**
 * Encapsulates a message containing a list of tasks.
 * It inherits from `DukeOutputMessage`.
 * It overrides methods in `DukeOutputMessage` to format the message differently for a list.
 */
public class DukeListMessage extends DukeOutputMessage{
    private static String LIST_PREFIX = "Here are the tasks in your list: ";

    /**
     * Constructor to instantiate a `DukeListMessage`.
     * Instantiates a parent `DukeOutputMessage`.
     *
     * @param message the string to be used in the list message
     */
    public DukeListMessage(String message) {
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
        return this.getMessage() + "\n\tヽ(°〇°)ﾉ";
    }
}
