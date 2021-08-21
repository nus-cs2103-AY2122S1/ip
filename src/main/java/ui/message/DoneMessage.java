package ui.message;

/**
 * Encapsulates a done message used to inform a user when a task is marked as done.
 * It inherits from `ui.message.DukeOutputMessage`.
 * It overrides methods in `ui.message.DukeOutputMessage` to format the message differently for marking a task as done.
 */
public class DoneMessage extends Message {
    private static String DONE_PREFIX = "Nice! I've marked this task as done:";

    /**
     * Constructor to instantiate a `ui.message.DukeDoneMessage`.
     * Instantiates a parent `ui.message.DukeOutputMessage`.
     *
     * @param message the string to be used in the done message
     */
    public DoneMessage(String message) {
        super(message,"≧(´▽｀)≦");
    }

    /**
     * Gets the message with an prefix informing that a task is marked as done
     *
     * @return the string message with the prefix
     */
    @Override
    protected String getMessage() {
        return DONE_PREFIX + "\n\t\t" + super.getMessage();
    }
}
