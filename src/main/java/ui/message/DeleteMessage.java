package ui.message;

/**
 * Encapsulates a deleted message used to inform a user that a message has been deleted from a list.
 * It inherits from `ui.message.DukeListChangedMessage`.
 * It overrides methods in `ui.message.DukeListChangedMessage` to format the message differently for deleting.
 */
public class DeleteMessage extends ListChangeMessage {
    private static String DELETE_PREFIX = "Noted. I've removed this task:";

    /**
     * Constructor to instantiate a `ui.message.DukeDeletedMessage`.
     *
     * @param message the string to be used in the deleted message.
     * @param itemsInList the number of items in the list after the change.
     */
    public DeleteMessage(String message, int itemsInList) {
        super(message, itemsInList);
    }

    /**
     * Gets the message with a prefix that the task was deleted.
     *
     * @return the string message with a prefix.
     */
    @Override
    protected String getMessage() {
        return String.format(
                "%s\n\t\t%s",
                DELETE_PREFIX,
                super.getMessage()
        );
    }
}
