package ui.message;

/**
 * Encapsulates a done message used to inform a user when a task is marked as done.
 */
public class DoneMessage extends Message {
    private static String DONE_PREFIX = "Nice! I've marked this task as done:";

    public DoneMessage(String message) {
        super(message,"≧(´▽｀)≦");
    }

    /**
     * Gets the message with an prefix informing that a task is marked as done.
     *
     * @return String message with the prefix.
     */
    @Override
    protected String getMessage() {
        return DONE_PREFIX + "\n\t\t" + super.getMessage();
    }
}
