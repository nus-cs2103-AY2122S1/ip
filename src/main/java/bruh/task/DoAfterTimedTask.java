package bruh.task;

/**
 * Represents a task to be done after a specific date & time.
 */
public class DoAfterTimedTask extends TimedTask {
    private static final char SYMBOL = 'A';

    /**
     * Constructor for a task do be done after a specific date & time.
     *
     * @param description     The description of the task.
     * @param doAfterDateTime The date & time to do the task after.
     * @param flag            The flag separating the description and deadline.
     */
    public DoAfterTimedTask(String description, String doAfterDateTime, String flag) {
        super(description, SYMBOL, doAfterDateTime, flag);
    }
}
