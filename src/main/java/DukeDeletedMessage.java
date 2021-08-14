/**
 * Encapsulates a deleted message used to inform a user that a message has been deleted from a list.
 * It inherits from `DukeListChangedMessage`.
 * It overrides methods in `DukeListChangedMessage` to format the message differently for deleting.
 */
public class DukeDeletedMessage extends DukeListChangedMessage {
    private static String DELETED_PREFIX = "Noted. I've removed this task:";

    /**
     * Constructor to instantiate a `DukeDeletedMessage`.
     *
     * @param message the string to be used in the deleted message.
     * @param itemsInList the number of items in the list after the change.
     */
    public DukeDeletedMessage(String message, int itemsInList) {
        super(message, itemsInList);
    }

    /**
     * Gets the message with a prefix that the task was deleted.
     *
     * @return the string message with a prefix.
     */
    @Override
    public String getMessage() {
        return String.format(
                "%s\n\t\t%s",
                DELETED_PREFIX,
                super.getMessage()
        );
    }
}
