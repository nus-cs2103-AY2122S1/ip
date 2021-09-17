package bruh.task;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTimedTask extends TimedTask {
    private static final char SYMBOL = 'D';

    /**
     * Constructor for a task with a deadline.
     *
     * @param description The description of the task.
     * @param deadline    The deadline of the task.
     * @param flag        The flag separating the description and deadline.
     */
    public DeadlineTimedTask(String description, String deadline, String flag) {
        super(description, SYMBOL, deadline, flag);
    }
}
