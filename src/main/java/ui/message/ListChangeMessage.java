package ui.message;

/**
 * Encapsulates a message used to inform a user that there is a change on the list.
 */
public class ListChangeMessage extends Message {
    private int itemsInList;

    /**
     * Instantiates a message describing a list change.
     *
     * @param message Message describing the list change.
     * @param itemsInList Number of items in the list.
     */
    public ListChangeMessage(String message, int itemsInList) {
        super(message, "(＾＾)b");
        this.itemsInList = itemsInList;
    }

    /**
     * Gets the message with information about the number of items remaining in the list.
     *
     * @return String message.
     */
    @Override
    protected String getMessage() {
        int numOfTasks = this.itemsInList;
        String task = numOfTasks == 1 ? "task" : "tasks";

        return String.format(
                "%s\n\tNow you have %d %s in the list.",
                super.getMessage(),
                numOfTasks,
                task);
    }
}
