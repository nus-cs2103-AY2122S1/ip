package bruh.task;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends TimedTask {
    /**
     * Constructor for a task with a deadline.
     *
     * @param description The description of the task.
     * @param deadline    The deadline of the task.
     */
    public Deadline(String description, String deadline) {
        super(description, 'D', deadline);
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), getDateTimeDesc());
    }
}
