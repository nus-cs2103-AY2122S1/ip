/**
 * Encapsulates a done message used to inform a user when a task is marked as done.
 * It inherits from `DukeOutputMessage`.
 * It overrides methods in `DukeOutputMessage` to format the message differently for marking a task as done.
 */
public class DukeDoneMessage extends DukeOutputMessage {
    private static String DONE_PREFIX = "Nice! I've marked this task as done: ";

    /**
     * Constructor to instantiate a `DukeDoneMessage`.
     * Instantiates a parent `DukeOutputMessage`.
     *
     * @param message the string to be used in the done message
     */
    public DukeDoneMessage(String message) {
        super(message);
    }

    /**
     * Gets the message with an prefix informing that a task is marked as done
     *
     * @return the string message with the prefix
     */
    @Override
    public String getMessage() {
        return DONE_PREFIX + "\n\t\t" + super.getMessage();
    }

    /**
     * Gets the message with a happy face added to it
     *
     * @return the string message with a happy face
     */
    @Override
    public String getMessageWithFace() {
        return this.getMessage() + " ≧(´▽｀)≦";
    }
}
