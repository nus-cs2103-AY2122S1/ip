/**
 * Encapsulates a added message used to inform a user that a message has been added to a list.
 * It inherits from `DukeOutputMessage`.
 * It overrides methods in `DukeOutputMessage` to format the message differently for adding.
 */
public class DukeAddedMessage extends DukeOutputMessage {
    private static String ADDED_PREFIX = "Got it. I've added this task:";
    private int itemsInList;

    /**
     * Constructor to instantiate a `DukeAddedMessage`.
     * Instantiates a parent `DukeOutputMessage`.
     *
     * @param message the string to be used in the added message
     */
    public DukeAddedMessage(String message, int itemsInList) {
        super(message);
        this.itemsInList = itemsInList;
    }

    /**
     * Gets the message with an `added: ` prefix
     *
     * @return the string message with an `added: ` prefix
     */
    @Override
    public String getMessage() {
        int numOfTasks = this.itemsInList;
        String task = numOfTasks == 1 ? "task" : "tasks";

        return String.format(
                "%s\n\t\t%s\n\tNow you have %d %s in the list.",
                ADDED_PREFIX,
                super.getMessage(),
                numOfTasks,
                task);
    }

    /**
     * Gets the message with a thumbs up face added to it
     *
     * @return the string message with a thumbs up face
     */
    @Override
    public String getMessageWithFace() {
        return this.getMessage() + " (＾＾)b";
    }
}
