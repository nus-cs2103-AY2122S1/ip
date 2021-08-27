package ui.message;

/**
 * Encapsulates a added message used to inform a user that a message has been added to a list.
 */
public class AddMessage extends ListChangeMessage {
    private static final String PREFIX_ADD = "Got it. I've added this task:";

    public AddMessage(String message, int itemsInList) {
        super(message, itemsInList);
    }

    /**
     * Gets the message with a prefix that the task was added.
     *
     * @return String message with a prefix.
     */
    @Override
    protected String getMessage() {
        return String.format(
                "%s\n\t\t%s",
                PREFIX_ADD,
                super.getMessage()
        );
    }
}
