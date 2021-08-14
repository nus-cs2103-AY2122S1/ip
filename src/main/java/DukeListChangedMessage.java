/**
 * Encapsulates a message used to inform a user that there is a change on the list.
 * It inherits from `DukeOutputMessage`.
 * It overrides methods in `DukeOutputMessage` to format the message differently for a list change.
 */
public class DukeListChangedMessage extends DukeOutputMessage {
    private int itemsInList;

    /**
     * Constructor to instantiate a `DukeListChangedMessage`.
     *
     * @param message the string to be used in the added message.
     * @param itemsInList the number of items in the list after the change.
     */
    public DukeListChangedMessage(String message, int itemsInList) {
        super(message);
        this.itemsInList = itemsInList;
    }

    /**
     * Gets the message with information about the number of items remaining in the list.
     *
     * @return the string message.
     */
    @Override
    public String getMessage() {
        int numOfTasks = this.itemsInList;
        String task = numOfTasks == 1 ? "task" : "tasks";

        return String.format(
                "%s\n\tNow you have %d %s in the list.",
                super.getMessage(),
                numOfTasks,
                task);
    }

    /**
     * Gets the message with a thumbs up face added to it.
     *
     * @return the string message with a thumbs up face.
     */
    @Override
    public String getMessageWithFace() {
        return this.getMessage() + " (＾＾)b";
    }
}
