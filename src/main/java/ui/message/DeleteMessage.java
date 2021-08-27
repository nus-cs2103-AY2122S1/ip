package ui.message;

/**
 * Encapsulates a deleted message used to inform a user that a message has been deleted from a list.
 */
public class DeleteMessage extends ListChangeMessage {
    private static final String PREFIX_DELETE = "Noted. I've removed this task:";

    public DeleteMessage(String message, int itemsInList) {
        super(message, itemsInList);
    }

    /**
     * Gets the message with a prefix that the task was deleted.
     *
     * @return String message with a prefix.
     */
    @Override
    protected String getMessage() {
        return String.format(
                "%s\n\t\t%s",
                PREFIX_DELETE,
                super.getMessage()
        );
    }
}
