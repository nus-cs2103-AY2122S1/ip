package bruh.task;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends TimedTask {
    private static final char SYMBOL = 'D';
    private static final String STRING_FORMAT = "%s (by: %s)";

    /**
     * Constructor for a task with a deadline.
     *
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String description, String deadline) {
        super(description, SYMBOL, deadline);
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT, super.toString(), getDateTimeDesc());
    }
}
