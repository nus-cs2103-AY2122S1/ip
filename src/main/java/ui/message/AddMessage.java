package ui.message;

/**
 * Encapsulates a added message used to inform a user that a message has been added to a list.
 * It inherits from `ui.message.DukeOutputMessage`.
 * It overrides methods in `ui.message.DukeOutputMessage` to format the message differently for adding.
 */
public class AddMessage extends ListChangeMessage {
    private static String ADDED_PREFIX = "Got it. I've added this task:";

    /**
     * Constructor to instantiate a `ui.message.DukeAddedMessage`.
     *
     * @param message the string to be used in the added message.
     * @param itemsInList the number of items in the list after the change.
     */
    public AddMessage(String message, int itemsInList) {
        super(message, itemsInList);
    }

    /**
     * Gets the message with a prefix that the task was added.
     *
     * @return the string message with a prefix.
     */
    @Override
    public String getMessage() {
        return String.format(
                "%s\n\t\t%s",
                ADDED_PREFIX,
                super.getMessage()
        );
    }
}
